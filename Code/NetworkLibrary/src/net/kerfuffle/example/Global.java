package net.kerfuffle.example;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.example.Packets.*;

public class Global {

	public static final int LOGIN = 0,
			DISCONNECT = 1,
			MESSAGE = 2;
	
	public static final int CLIENT = 0, SERVER = 1;
	
	public static int Type = -1;
}
