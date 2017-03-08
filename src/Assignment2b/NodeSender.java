package Assignment2b;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class NodeSender extends Thread {
	int portown;
	int portsend;
	String s;
	InetAddress group;
	MulticastSocket socket;
	NodeSender(int port_o,int port_s, String s1)
	{
		portown = port_o;
		portsend=port_s;
		s = s1;
	}
	
	public void run() {
	try{
		
			group = InetAddress.getByName("228.5.6.10");
			socket = new MulticastSocket(portown);
			socket.joinGroup(group);
			DatagramPacket packet1 = new DatagramPacket(s.getBytes(), s.length(),group, portsend);
			socket.send(packet1);
			socket.close();
			 
		}
		 catch(Exception e){
			  
		 }
	}
	
	

}
