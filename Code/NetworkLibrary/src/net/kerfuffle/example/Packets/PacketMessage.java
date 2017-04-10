package net.kerfuffle.example.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.example.Global;

public class PacketMessage extends Packet{

	private static final int id = Global.MESSAGE;
	
	private String message;
	
	public PacketMessage(String data)
	{
		super(data, id, null, -1);
		
		if (data.contains(","))
		{
			String sp[] = data.split(",");
			message = sp[1];
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
	public String getSendData()
	{
		return (id+","+message+",");
	}
}
