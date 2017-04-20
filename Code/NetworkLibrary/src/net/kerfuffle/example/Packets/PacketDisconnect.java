package net.kerfuffle.example.Packets;

import java.net.InetAddress;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.example.Global;

public class PacketDisconnect extends Packet{
	
	private String message, username;
	
	public PacketDisconnect(String data, InetAddress ip, int port)
	{
		super(data, Global.DISCONNECT, ip, port);
		
		if (Global.Type == Global.CLIENT)
		{
			String sp[] = data.split(",");
			username = sp[1];
			message = sp[2];
		}
		else if (Global.Type == Global.SERVER)
		{
			String sp[] = data.split(",");
			message = sp[1];
		}
		
	}
	public PacketDisconnect(String message)
	{
		this.message=message;
		data = (Global.DISCONNECT + "," + message + ",");
	}
	public PacketDisconnect (String username, String message)
	{
		this.message=message;
		data = (Global.DISCONNECT+","+username+","+message+",");
	}
	
	public String getMessage()
	{
		return message;
	}
	public String getUsername()
	{
		return username;
	}
}
