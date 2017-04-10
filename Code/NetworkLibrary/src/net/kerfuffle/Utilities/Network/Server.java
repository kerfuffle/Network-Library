package net.kerfuffle.Utilities.Network;

import static net.kerfuffle.Utilities.Network.Packet.*;

import java.util.ArrayList;

public class Server implements Runnable{
	
	private int port;
	private boolean running = false;
	private MyCode myCode;
	
	private ArrayList <User> users = new ArrayList<User>();
	
	public Server(String threadName, int port)
	{
		this.port = port;
	}

	public void setMyCode(MyCode myCode)
	{
		this.myCode = myCode;
	}
	
	public void start()
	{
		running = true;
		//start thread
	}
	
	public void run()
	{
		Packet incoming = receivePacket(port);
		myCode.run(incoming);
	}

	
	
	public int getPort()
	{
		return port;
	}
	public void addUser(User u)
	{
		users.add(u);
	}
	public void removeUser(User u)
	{
		users.remove(u);
	}
	public void removeUser (int i)
	{
		users.remove(i);
	}
	
}
