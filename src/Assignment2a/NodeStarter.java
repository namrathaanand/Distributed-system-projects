package Assignment2a;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class NodeStarter extends Thread {
	int portown;
	int portsend;
	String s;
	InetAddress group;
	MulticastSocket socket;
	NodeStarter(int port_o)
	{
		portown = port_o;
	}
	
	public void run() {
		
		synchronized(this){
			try {
				group = InetAddress.getByName("228.5.6.10");
				socket = new MulticastSocket(portown);
				socket.joinGroup(group);
				byte[] buf = new byte[100];
				 DatagramPacket recv = new DatagramPacket(buf, buf.length);
				 socket.receive(recv);
				 s = new String(buf,recv.getOffset(),recv.getLength());
				 notify();
			}
		 catch(Exception e){
			  
		 }
			
	}
}
}