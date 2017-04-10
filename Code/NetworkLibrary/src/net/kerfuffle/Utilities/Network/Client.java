package net.kerfuffle.Utilities.Network;

import java.net.InetAddress;
import java.util.ArrayList;

import static net.kerfuffle.Utilities.Network.Packet.*;

public class Client implements Runnable{

	private Thread thread;
	private String threadName;
	private MyCode myCode;
	
	private InetAddress ip;
	private int port;
	private boolean running = false;
	
	private ArrayList <User> users = new ArrayList <User>();
	
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
	
	
	
	
	public void addUser(User u)
	{
		users.add(u);
	}
	public void removeUser(User u)
	{
		users.remove(u);
	}
	public void removeUser(int i)
	{
		users.remove(i);
	}
	public void removeUser(InetAddress ip, int port)
	{
		for (User u : users)
		{
			if (u.getIp().toString().equals(ip.toString()) && u.getPort() == port)
			{
				users.remove(u);
				return;
			}
		}
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
