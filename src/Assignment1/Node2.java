package Assignment1;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class Node2 {

	/**
	 * @param args
	 */
	static int socket1=6785;
	static int socket2=6895;
	static int socket3=8756;
	static int socketrec1 = 6789;
	static int socketrec2 = 6788;
	static int socketrec3 = 6787;
	public static void main(String[] args) {
		InetAddress group;
		try {
			int counterm = (int )(Math.random() * 50 + 1);
			int countsender;
			int senddiff;
			group = InetAddress.getByName("228.5.6.7");
			MulticastSocket s = new MulticastSocket(socket2);
			 s.joinGroup(group);

			 //Printing it own counter
			 System.out.println("Node 2:");
			 System.out.println("Current Node 2 clock: "+counterm);;
			 
			 
			 byte[] buf = new byte[100];
			 DatagramPacket recv = new DatagramPacket(buf, buf.length);
			 s.receive(recv);
			 String a1 = new String(buf,recv.getOffset(),recv.getLength());
			 countsender= Integer.parseInt(a1);
			 System.out.println("Deamon clock time: "+countsender);
			 
			//calculate the diff
			 senddiff=countsender-counterm;

			 //Send diff back
			 String msg=""+senddiff;
			 System.out.println("Sending clock difference to Time Deamon:");
			 DatagramPacket DiffPackage = new DatagramPacket(msg.getBytes(), msg.length(),
                     group, socketrec2);
			 s.send(DiffPackage);
			 
			 //Receive Settleup 
			 
			 byte[] bufdif = new byte[100];
			 DatagramPacket recvdif = new DatagramPacket(bufdif, bufdif.length);
			 s.receive(recvdif);
			 String dif = new String(bufdif,recvdif.getOffset(),recvdif.getLength());
			
			
			 //Resetting the clock
			 System.out.println("Clock Correction received from Time Deamon: "+Integer.parseInt(dif));
			 counterm= counterm + Integer.parseInt(dif);
			 
//Printing the final clock
			 
			 System.out.println("Syncronized Node 2 clock :"+counterm);
			 
			 
			 
			 // OK, I'm done talking - leave the group...
			 s.leaveGroup(group);
			 s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}
