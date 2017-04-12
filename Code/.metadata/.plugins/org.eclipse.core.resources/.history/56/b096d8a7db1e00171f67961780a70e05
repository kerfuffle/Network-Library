package net.kerfuffle.example;

import java.util.Scanner;

public class Game implements Runnable{

	private Thread t;
	private String threadName, nextMessage;
	private boolean running = false;
	
	public Game(String threadName)
	{
		this.threadName = threadName;
	}
	
	public void run()
	{
		while (running)
		{
			Scanner scan = new Scanner(System.in);
			nextMessage = scan.nextLine();
			
			// maybe send packet from here
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
