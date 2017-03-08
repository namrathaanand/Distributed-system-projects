package Bonus;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class NodeReceiver extends Thread {
	int portown;
	int portsend;
	String s;
	InetAddress group;
	MulticastSocket socket;
	byte[] buf; 
	DatagramPacket recv ;
	NodeReceiver(int port_o)
	{
		portown = port_o;
	}
	
	public void run() {
		
		for(int i =0;i!=20;i++){
			try {
				group = InetAddress.getByName("228.5.6.10");
				socket = new MulticastSocket(portown);
				socket.joinGroup(group);
				buf = new byte[100];
				 recv = new DatagramPacket(buf, buf.length);
				 socket.receive(recv);
				 s = new String(buf,recv.getOffset(),recv.getLength());
				 System.out.println(s);
			}
		 catch(Exception e){
			  
		 }
		}
			
			
	
}
}