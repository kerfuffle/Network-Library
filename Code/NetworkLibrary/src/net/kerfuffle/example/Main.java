package net.kerfuffle.example;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import net.kerfuffle.Utilities.Network.Client;
import net.kerfuffle.Utilities.Network.MyCode;
import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.Utilities.Network.Server;

public class Main {

	private final int CLIENT = 0, SERVER = 1;
	
	private Client client;
	private Server server;
	private int type = -1;
	
	public Main(int type) throws NumberFormatException, UnknownHostException
	{
		this.type = type;
		if (type == CLIENT)
		{
			String in = JOptionPane.showInputDialog("Ip:Port");
			String sp[] = in.split(":");
			client = new Client("Client Thread", InetAddress.getByName(sp[0]), Integer.parseInt(sp[1]));
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
							Packet p = (Packet)obj[0];
							
							
						}
					});
			
			
			client.start();
		}
		else if (type == SERVER)
		{
			server.start();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main (String args[]) throws NumberFormatException, UnknownHostException
	{
		int type = JOptionPane.showOptionDialog(null, "Server or Client?", "Type",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, new String[]{"Client", "Server"}, "Client");
	
		Main main = new Main(type);
		
		main.start();
		
	}
	
}
