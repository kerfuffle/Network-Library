package net.kerfuffle.example.Packets;

import java.net.InetAddress;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.example.Global;

public class PacketLogin extends Packet{
	
	private final static int id = Global.LOGIN;
	
	private String username;
	
	public PacketLogin(String data)
	{
		super(data, id, null, -1);
		
		if (data.contains(","))	// means it is coming from server
		{
			String sp[] = data.split(",");
			username = sp[1];
		}
		else	//means it is coming from client
		{
			username = data;
		}
	}
	
	public String getUsername()
	{
		return username;
	}
	public String getSendData()
	{
		return (id + "," + username + ",");
	}
	
}
