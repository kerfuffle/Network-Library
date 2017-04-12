package net.kerfuffle.example.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.example.Global;

public class PacketMessage extends Packet{

	private static final int id = Global.MESSAGE;
	
	private String message, username;
	
	public PacketMessage(String data)
	{
		super(data, id);
		
		if (data.contains(","))	//from server, being processed by client
		{
			String sp[] = data.split(",");
			username = sp[1];
			message = sp[2];
		}
	}
	public PacketMessage(String username, String message)
	{
		super("Sending From Server", id);
		
		this.username=username;
		this.message=message;
	}
	
	public String getMessage()
	{
		return message;
	}
	public String getClientSendData()
	{
		return (id+","+message+",");
	}
	public String getServerSendData()
	{
		return (id+","+username+","+message+",");
	}
}
