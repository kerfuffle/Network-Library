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
	private int type = -1;
	
	public Main(int type) throws NumberFormatException, IOException
	{
		this.type = type;
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
		if (type == CLIENT)
		{
			client.setMyCode(new MyCode()
					{
						public void run(Object... obj) 
						{
							while (client.isRunning())
							{
								Packet packet = (Packet)obj[0];
								
								if (packet.getId() == Global.LOGIN)
								{
									PacketLogin p = (PacketLogin)packet;
									User u = new User(p.getUsername());	//for client the ip and port would just be the server
									client.addUser(u);
								}
								if (packet.getId() == Global.DISCONNECT)
								{
									PacketDisconnect p = (PacketDisconnect)packet;
									System.out.println("***" + p.getUsername() + " disconnected because " + p.getMessage());
									client.removeUser(p.getIp(), p.getPort());
								}
								if (packet.getId() == Global.MESSAGE)
								{
									PacketMessage p = (PacketMessage)packet;
									System.out.println(">>"+ client.getUsername(p.getIp(), p.getPort()) + ": " + p.getMessage());
								}
							}
						}
					});
			
			
			client.start();
			
			game.start();
		}
		else if (type == SERVER)
		{
			server.setMyCode(new MyCode()
					{
						public void run(Object... obj) throws IOException
						{
							while (server.isRunning())
							{
								Packet packet = (Packet)obj[0];
								
								if (packet.getId() == Global.LOGIN)
								{
									PacketLogin p = (PacketLogin)packet;
									User u = new User(p.getUsername(), p.getIp(), p.getPort());
									server.addUser(u);
									
									PacketLogin send = new PacketLogin(p.getUsername());
									server.sendToAllUsers(send);
								}
								if (packet.getId() == Global.DISCONNECT)
								{
									PacketDisconnect p = (PacketDisconnect)packet;
									System.out.println("***" + p.getUsername() + " disconnected because " + p.getMessage());
									server.removeUser(p.getIp(), p.getPort());
									
									PacketDisconnect send = new PacketDisconnect(p.getUsername(), p.getMessage());
									server.sendToAllUsers(send);
									//tell all clients a user has disconnected
								}
								if (packet.getId() == Global.MESSAGE)
								{
									PacketMessage p = (PacketMessage)packet;
									
									PacketMessage send = new PacketMessage(server.getUsername(p.getIp(), p.getPort()), p.getMessage());
									server.sendToAllUsers(send);
								}
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
