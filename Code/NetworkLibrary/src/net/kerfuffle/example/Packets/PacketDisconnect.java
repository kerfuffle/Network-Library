package net.kerfuffle.example.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.example.Global;

public class PacketDisconnect extends Packet{

	private final static int id = Global.DISCONNECT;
	
	public PacketDisconnect(String data)
	{
		super(data, id, null, -1);
	}
}
