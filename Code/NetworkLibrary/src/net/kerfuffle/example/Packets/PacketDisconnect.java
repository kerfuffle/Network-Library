package net.kerfuffle.example.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.example.Global;

public class PacketDisconnect extends Packet{

	private final static int id = Global.DISCONNECT;
	
	private String message, username;
	
	public PacketDisconnect(String data)
	{
		super(data, id);
		
		if (data.contains(","))
		{
			String sp[] = data.split(",");
			username = sp[1];
			message = sp[2];
		}
		else
		{
			message = data;
		}
	}
	
	public String getMessage()
	{
		return message;
	}
	public String getUsername()
	{
		return username;
	}
	
	public String getClientSendData()
	{
		return (id + "," + message + ",");
	}
	public String getServerSendData()
	{
		return (id + "," + username + "," + message + ",");
	}
}
