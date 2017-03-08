package Assignment2a;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NodeReceiver extends Thread {
	int portown;
	int portsend;
	String s;
	String s1;
	InetAddress group;
	MulticastSocket socket;
	byte[] buf; 
	int ack=0;
	int ack1=6666;
	int ack2=6667;
	int ack3=6668;
	int ackalt;
	DatagramPacket recv ;
	DatagramPacket packet1;
	List<String> Buffer = new ArrayList<String>();
	NodeReceiver(int port_o)
	{
		portown = port_o;
	}
	
	public void run() {
		
		if(portown==5555){
			ack=ack1;
			ackalt=ack2;
		}
		else if (portown==5678)
		{
			ack=ack1;
			ackalt=ack3;			
		}
		else {
			ack=ack2;
			ackalt=ack3;
			}
		
			 

		
		for(int i =0;i!=3;i++){
			try {
				group = InetAddress.getByName("228.5.6.10");
				socket = new MulticastSocket(portown);
				socket.joinGroup(group);
				buf = new byte[100];
				 recv = new DatagramPacket(buf, buf.length);
				 socket.receive(recv);
				 s = new String(buf,recv.getOffset(),recv.getLength());
				 if(ack!=0){
					 s1="Message Received";
					 packet1 = new DatagramPacket(s1.getBytes(), s1.length(),group, ack);
					 socket.send(packet1);
					 packet1 = new DatagramPacket(s1.getBytes(), s1.length(),group, ackalt);
					 socket.send(packet1);  		 
				 }
				 
				 Buffer.add(s);
				 Collections.sort(Buffer);
				 
			}
		 catch(Exception e){
			 
			 e.printStackTrace(); 
		 }
		}
			
			
	
}
}