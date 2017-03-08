package Assignment1;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class TimeDeamon {

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
		// TODO Auto-generated method stub
		String msg = "Hello from Sender thread";
		int counterm = (int )(Math.random() * 50 + 1);
		int counter1;
		int counter2;
		int counter3;
		int diff1;
		int diff2;
		int diff3;
		int settleup;
		 InetAddress group;
		try {
			group = InetAddress.getByName("228.5.6.7");
			MulticastSocket s = new MulticastSocket(socketrec1);
			MulticastSocket s2 = new MulticastSocket(socketrec2);
			MulticastSocket s3 = new MulticastSocket(socketrec3);
			
			 s.joinGroup(group);
			 s2.joinGroup(group);
			 s3.joinGroup(group);
			 
			 //Prints the counter
			 System.out.println("DS Project2: \nNamratha Anand\nParth Desai");
			 System.out.println("Assignment 1");
			 System.out.println("Time Deamon:");
			 System.out.println("Current Time Deamon clock: "+counterm);
			 
			 //initialize the message as counter
			 msg=""+counterm;
			 
			 //D sends to 1
			 System.out.println("Polling node 1 for clock....");
			 
			 DatagramPacket packet1 = new DatagramPacket(msg.getBytes(), msg.length(),
                     group, socket1);
			 s.send(packet1);
			 
			 //D sends to 2
			 
			 System.out.println("Polling node 2 for clock....");
			 msg=""+counterm;
			 DatagramPacket packet2 = new DatagramPacket(msg.getBytes(), msg.length(),
                     group, socket2);
			 s2.send(packet2);
			 //D sends to 3
			 System.out.println("Polling node 3 for clock....");
			 
			 msg=""+counterm;
			 DatagramPacket packet3 = new DatagramPacket(msg.getBytes(), msg.length(),
                     group, socket3);
			 s3.send(packet3);
			 
			 //Receive from 1
			 System.out.println("Receiving the message from node 1");
			 byte[] buf1 = new byte[100];
			 DatagramPacket recvpack1 = new DatagramPacket(buf1, buf1.length);
			 s.receive(recvpack1);
			 String Recv1 = new String(buf1,recvpack1.getOffset(),recvpack1.getLength());
			 counter1= Integer.parseInt(Recv1);
			 
			 System.out.println("Clock Difference from node 1:"+counter1);
			 
			 
			 //Receive from 2
			 System.out.println("Receiving the message from node 2");
			 byte[] buf2 = new byte[100];
			 DatagramPacket recvpack2 = new DatagramPacket(buf2, buf2.length);
			 s2.receive(recvpack2);
			 String Recv2 = new String(buf2,recvpack2.getOffset(),recvpack2.getLength());
			 counter2= Integer.parseInt(Recv2);
			 
			 System.out.println("Clock Difference from node 2:"+counter2);
			 
			 //Receive from 3
			 
			 System.out.println("Receiving the message from node 3");
			 byte[] buf3 = new byte[100];
			 DatagramPacket recvpack3 = new DatagramPacket(buf3, buf3.length);
			 s3.receive(recvpack3);
			 String Recv3 = new String(buf3,recvpack3.getOffset(),recvpack3.getLength());
			 counter3= Integer.parseInt(Recv3);
			 
			 System.out.println("Clock Difference from node 3:"+counter3);
			 
			 //Calculate Average between Time Deamon, Receiver1, Receiver2 & Receiver3 
			 settleup = (counter1+counter2+counter3+(counterm-counterm))/4;
			 diff1=counter1+settleup;
			 diff2=counter2+settleup;
			 diff3=counter3+settleup;
			 counterm = counterm + settleup;
			 
			 //Send AVG back to 1
			 
			 System.out.println("Clock correction for node1 :"+diff1);
			 msg=""+diff1;
			 DatagramPacket diffpacket1 = new DatagramPacket(msg.getBytes(), msg.length(),
                     group, socket1);
			 s.send(diffpacket1);
			 
			 //Send AVG back to 2
			 
			 System.out.println("Clock correction for node2 :"+diff2);
			 msg=""+diff2;
			 DatagramPacket diffpacket2 = new DatagramPacket(msg.getBytes(), msg.length(),
                     group, socket2);
			 s.send(diffpacket2);
			 
			 
			 //Send AVG back to 3
			 
			 System.out.println("Clock correction for node3 :"+diff3);
			 msg=""+diff3;
			 DatagramPacket diffpacket3 = new DatagramPacket(msg.getBytes(), msg.length(),
                     group, socket3);
			 s.send(diffpacket3);
			 
			 
			 //Printing the final clock
			 
			 System.out.println("Syncronized Deamon clock :"+counterm);
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 // Closing off the resources
			 s.leaveGroup(group);
			 s.close();
			 s2.close();
			 s3.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
