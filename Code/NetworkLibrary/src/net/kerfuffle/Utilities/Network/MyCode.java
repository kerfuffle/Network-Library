package net.kerfuffle.Utilities.Network;

public abstract class MyCode {

	/**
	 * For the sake of this network library, obj[0] will always be the incoming packet.
	 */
	public abstract void run(Object... obj);
	
}
