package net.kerfuffle.Utilities.Network;

import static net.kerfuffle.Utilities.Network.Packet.*;

public class Server implements Runnable{
	
	private int port;
	private boolean running = false;
	
	private MyCode myCode;
	
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
	
}
