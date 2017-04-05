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
	
	public Client(String threadName, InetAddress ip, int port)
	{
		this.threadName=threadName;
		this.ip = ip;
		this.port=port;
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
		Packet incoming = receivePacket(ip, port);
		myCode.run(incoming);
	}
	
	
	
	
	
	
	public InetAddress getIp()
	{
		return ip;
	}
	public int getPort()
	{
		return port;
	}

	
}
