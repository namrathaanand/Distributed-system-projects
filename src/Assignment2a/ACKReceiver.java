package Assignment2a;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ACKReceiver extends Thread {
	int portown;
	int portsend;
	String s;
	InetAddress group;
	MulticastSocket socket;
	byte[] buf; 
	DatagramPacket recv ;
	List<String> Buffer = new ArrayList<String>();
	ACKReceiver(int port_o)
	{
		portown = port_o;
	}
	
	public void run() {
		
		for(int i =0;i!=10;i++){
			try {
				group = InetAddress.getByName("228.5.6.10");
				socket = new MulticastSocket(portown);
				socket.joinGroup(group);
				buf = new byte[100];
				 recv = new DatagramPacket(buf, buf.length);
				 socket.receive(recv);
				 s = new String(buf,recv.getOffset(),recv.getLength());
				 
				 Buffer.add(s);
				 Collections.sort(Buffer);
			}
		 catch(Exception e){
			 
			 e.printStackTrace(); 
		 }
		}
			
			
	
}
}