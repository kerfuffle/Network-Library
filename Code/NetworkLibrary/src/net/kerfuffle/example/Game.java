package net.kerfuffle.example;

import java.io.IOException;
import java.util.Scanner;

import net.kerfuffle.Utilities.Network.Client;
import net.kerfuffle.example.Packets.PacketMessage;

public class Game implements Runnable{

	private Thread t;
	private String threadName, nextMessage;
	private boolean running = false;
	private Client client;
	
	public Game(String threadName, Client client)
	{
		this.threadName = threadName;
		this.client = client;
	}
	
	public void run()
	{
		while (running)
		{
			Scanner scan = new Scanner(System.in);
			nextMessage = scan.nextLine();
			
			PacketMessage pm = new PacketMessage(nextMessage);
			try 
			{
				client.sendPacket(pm);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void start()
	{
		running = true;
		t = new Thread(this, threadName);
		t.start();
	}
	
	public void setRunning(boolean b)
	{
		running = b;
	}
	public String getNextMessage()
	{
		return nextMessage;
	}
}
