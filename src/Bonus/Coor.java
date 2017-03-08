package Bonus;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.regex.Pattern;


public class Coor {

	/**
	 * @param args
	 */
	
	public static int send(int porto,int ports,String s1){
		try{
			NodeSender t1 = new NodeSender(porto,ports,s1);	 
			t1.start();
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
	
	public static void receive(int porto){
		
		
		NodeReceiver t1 = new NodeReceiver(porto);	 
		t1.start();
		
		
		try{
		
//			return t1.s;
			
		}catch(Exception e){
        	System.out.println("Main me gadbad");
            e.printStackTrace();
//            return "Error receiving";
            
        }
}
	
public static void start(int porto){
	NodeStarter t1 = new NodeStarter(porto);	 
		t1.start();
		synchronized(t1){
		try{
			t1.wait();
			
			
		}catch(Exception e){
        	System.out.println("Main me gadbad");
            e.printStackTrace();
            
        }
	}
}
	

	public static void main(String[] args) {

		int porto=2222;
		int port3=5555;
		int ports=5678;
		int portot=4679;
		int portstart=1234;
		int owner=0;
		int lock=0;
		InetAddress group;
		MulticastSocket socket;
		byte[] buf;
		String[] parts;
		Counter c = new Counter();
		c.value="0";
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("G:\\file.txt");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(c);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in G:\\file.txt");
	      }catch(Exception e) {
	         
	      }
		
		start(portstart);
				
		for(;;)
		{
				try {
					group = InetAddress.getByName("228.5.6.10");
					socket = new MulticastSocket(porto);
					socket.joinGroup(group);
					buf = new byte[100];
					DatagramPacket recv = new DatagramPacket(buf, buf.length);
					socket.receive(recv);
					String s = new String(buf,recv.getOffset(),recv.getLength());
					System.out.println(s);
					parts = s.split(Pattern.quote(":"));
					
					if(Integer.parseInt(parts[0])!= owner && parts[1].equals("Permission?") && lock == 0)
					{
						System.out.println("Permission granted to"+Integer.parseInt(parts[0]));
						lock=1;
						System.out.println("Resource Locked");
						owner=Integer.parseInt(parts[0]);
						s=porto+":yes";
						DatagramPacket packet1 = new DatagramPacket(s.getBytes(), s.length(),group, Integer.parseInt(parts[0]));
						socket.send(packet1);
					}
					else if( parts[1].equals("done") && lock==1)
					{
						System.out.println("Resource Acquired back.");
						System.out.println("Lock Released");
						lock=0;
						owner=0;
					}
					else{
						s=porto+":deny";
						System.out.println("Permission denied to:"+Integer.parseInt(parts[0]));
						DatagramPacket packet2 = new DatagramPacket(s.getBytes(), s.length(),group,Integer.parseInt(parts[0]));
						socket.send(packet2);
					}
										
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
				
				
				
				
				
				
		
	}
}