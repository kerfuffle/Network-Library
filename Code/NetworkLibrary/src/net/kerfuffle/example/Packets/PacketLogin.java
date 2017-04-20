package net.kerfuffle.example.Packets;

import java.net.InetAddress;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.example.Global;

public class PacketLogin extends Packet{
	
	private String username;
	
	public PacketLogin(String data, InetAddress ip, int port)
	{
		super(data, Global.LOGIN, ip, port);
		
		String sp[] = data.split(",");
		username = sp[1];
	}
	public PacketLogin(String username)
	{
		this.username = username;
		data = (Global.LOGIN + "," + username + ",");
	}
	
	
	public String getUsername()
	{
		return username;
	}
}
