package net.kerfuffle.Utilities.Network;

import java.net.InetAddress;

import static net.kerfuffle.Utilities.Network.Packet.*;

public class Client implements Runnable{

	private Thread thread;
	private String threadName;
	private MyCode myCode;
	
	private InetAddress ip;
	private int port;
	private boolean running = false;
	
	public Client(String threadName)
	{
		this.threadName=threadName;
	}
	public void start()
	{
		running = true;
		// start the thread
	}
	
	public void setMyCode(MyCode myCode)
	{
		this.myCode = myCode;
	}
	
	public void run()
	{
		Packet incoming = receivePacket();
		myCode.run(incoming);
	}
	

	
}
