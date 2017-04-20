package net.kerfuffle.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import net.kerfuffle.Utilities.Network.Client;
import net.kerfuffle.Utilities.Network.MyCode;
import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.Utilities.Network.Server;
import net.kerfuffle.Utilities.Network.User;
import net.kerfuffle.example.Packets.*;

public class Main {

	private final int CLIENT = 0, SERVER = 1;

	private Client client;
	private Server server;
	private Game game;

	public Main(int type) throws NumberFormatException, IOException
	{
		Global.Type = type;
		if (type == CLIENT)
		{
			String in = JOptionPane.showInputDialog("Ip:Port");
			String sp[] = in.split(":");
			String username = JOptionPane.showInputDialog("Username");
			client = new Client("Client Thread", InetAddress.getByName(sp[0]), Integer.parseInt(sp[1]));

			PacketLogin login = new PacketLogin(username);
			client.sendPacket(login);

			game = new Game("Game Thread", client);
		}
		else if (type == SERVER)
		{
			String in = JOptionPane.showInputDialog("Port");
			server = new Server("Server Thread", Integer.parseInt(in));
		}
	}

	public void start()
	{
		if (Global.Type == CLIENT)
		{
			client.setMyCode(new MyCode()
			{
				public void run(Packet packet) 
				{

					if (packet.getId() == Global.LOGIN)
					{
						PacketLogin p = new PacketLogin(packet.getData(), packet.getIp(), packet.getPort());
						User u = new User(p.getUsername());	//for client the ip and port would just be the server
						client.addUser(u);
					}
					if (packet.getId() == Global.DISCONNECT)
					{
						PacketDisconnect p = new PacketDisconnect(packet.getData(), packet.getIp(), packet.getPort());
						System.out.println("***" + p.getUsername() + " disconnected because: " + p.getMessage());
						client.removeUser(p.getUsername());
					}
					if (packet.getId() == Global.MESSAGE)
					{
						PacketMessage p = new PacketMessage(packet.getData(), packet.getIp(), packet.getPort());
						System.out.println(p.getMessage());
					}

				}
			});

			client.start();
			game.start();
		}
		else if (Global.Type == SERVER)
		{
			server.setMyCode(new MyCode()
			{
				public void run(Packet packet) throws IOException
				{
					if (packet.getId() == Global.LOGIN)
					{
						PacketLogin p = new PacketLogin(packet.getData(), packet.getIp(), packet.getPort());
						User u = new User(p.getUsername(), p.getIp(), p.getPort());
						server.addUser(u);

						System.out.println("** " + u.getUsername() + " has joined!");

						PacketLogin send = new PacketLogin(p.getUsername());
						server.sendToAllUsersExcept(send, p.getIp(), p.getPort());
					}
					if (packet.getId() == Global.DISCONNECT)
					{
						PacketDisconnect p = new PacketDisconnect(packet.getData(), packet.getIp(), packet.getPort());
						String user = server.getUsername(p.getIp(), p.getPort());
						System.out.println("***" + user + " disconnected because: " + p.getMessage());
						server.removeUser(p.getIp(), p.getPort());

						PacketDisconnect send = new PacketDisconnect(user, p.getMessage());
						server.sendToAllUsersExcept(send, p.getIp(), p.getPort());
						//tell all clients a user has disconnected
					}
					if (packet.getId() == Global.MESSAGE)
					{
						PacketMessage p = new PacketMessage(packet.getData(), packet.getIp(), packet.getPort());

						String message = "*"+server.getUsername(p.getIp(), p.getPort()) + ": " + p.getMessage();
						PacketMessage send = new PacketMessage(message);
						server.sendToAllUsersExcept(send, p.getIp(), p.getPort());
					}
				}

			});
			server.start();
		}
	}












	public static void main (String args[]) throws NumberFormatException, IOException
	{
		int type = JOptionPane.showOptionDialog(null, "Server or Client?", "Type",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, new String[]{"Client", "Server"}, "Client");

		Main main = new Main(type);

		main.start();

	}

}
