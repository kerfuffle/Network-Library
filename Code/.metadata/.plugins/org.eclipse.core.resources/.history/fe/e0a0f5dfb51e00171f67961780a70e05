package net.kerfuffle.Utilities.Network;

import java.net.InetAddress;

public abstract class Packet {
	
	private int id = -1;
	private String data;
	
	private InetAddress ip;
	private int port;
	
	/**
	 * Need to define packet ids in some Global class (outside of this library)
	 * @param data
	 */
	public Packet(String data, int id, InetAddress ip, int port)
	{
		this.data=data;
		this.id = id;
	}
	
	
	public String getData()
	{
		return data;
	}
	public int getId()
	{
		return id;
	}
	public InetAddress getIp()
	{
		return ip;
	}
	public int getPort()
	{
		return port;
	}
	
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
