package net.kerfuffle.example.Packets;

import java.net.InetAddress;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.example.Global;

public class PacketMessage extends Packet{
	
	private String message;
	
	public PacketMessage(String data, InetAddress ip, int port)
	{
		super(data, Global.MESSAGE, ip, port);
		
		String sp[] = data.split(",");
		message = sp[1];
	}
	public PacketMessage (String message)
	{
		this.message=message;
		data = (Global.MESSAGE+","+message+",");
	}
	
	public String getMessage()
	{
		return message;
	}
}
