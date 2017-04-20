package net.kerfuffle.example;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.example.Packets.*;

public class Global {

	public static final int LOGIN = 0,
			DISCONNECT = 1,
			MESSAGE = 2;
	
	public static Packet processPacket(Packet p, int id)
	{
		if (id == LOGIN)			//receive
		{
			return (PacketLogin)p;
		}
		if (id == DISCONNECT)		//receive and send
		{
			return (PacketDisconnect)p;
		}
		if (id == MESSAGE)
		{
			return (PacketMessage)p;
		}
		
		return null;
	}
}
