package net.kerfuffle.Utilities.Network;

import java.net.InetAddress;

public abstract class Packet {
	
	private int id = -1;
	
	
	//For client
	public static Packet receivePacket(InetAddress ip, int port)
	{
		return null;
	}

	// For server
	public static Packet receivePacket(int port)
	{
		return null;
	}
	
}
