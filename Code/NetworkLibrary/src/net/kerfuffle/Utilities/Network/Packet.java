package net.kerfuffle.Utilities.Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import net.kerfuffle.example.Global;

public class Packet {
	
	private int id = -1;
	private String data;
	
	private InetAddress ip;
	private int port;
	
	/**
	 * Need to define packet ids in some Global class (outside of this library)
	 * @param data
	 */
	public Packet(String data, int id, InetAddress ip, int port)
	{
		this.data=data;
		this.id = id;
		this.ip=ip;
		this.port=port;
	}
	public Packet(String data, InetAddress ip, int port)
	{
		this.data=data;
		this.ip=ip;
		this.port=port;
	}
	public Packet(String data, int id)
	{
		this.data=data;
		this.id=id;
	}
	
	public String getData()
	{
		return data;
	}
	public int getId()
	{
		return id;
	}
	public InetAddress getIp()
	{
		return ip;
	}
	public int getPort()
	{
		return port;
	}
	
	public static void sendPacket(Packet p, DatagramSocket socket, InetAddress ip, int port) throws IOException
	{
		if (p.toString() == null)
		{
			return;
		}
		
		byte buffer[] = p.toString().getBytes();
		DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, ip, port);
		socket.send(sendPacket);
	}
	
	//TODO
	public static Packet receivePacket(DatagramSocket socket) throws IOException
	{
		byte buffer[] = new byte[256];
		DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
		socket.receive(receivePacket);
		
		String data = new String(buffer);
		String sp[] = data.split(",");
		
		Packet p = new Packet(data, receivePacket.getAddress(), receivePacket.getPort());
		
		System.out.println(sp[0]);
		int id = Integer.parseInt(sp[0]);
		
		return Global.processPacket(p, id);
	}
	
}
