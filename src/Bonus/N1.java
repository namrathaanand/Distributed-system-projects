package Bonus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.regex.Pattern;


public class N1 {

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
	
public static void receive(int porto){
		
		
		NodeReceiver t1 = new NodeReceiver(porto);	 
		t1.start();
		
}
	
	
	public static void main(String[] args) {
		
		int porto=5678;
		int ports=4679;
		int portot=5555;
		int portstart=1234;
		int portcoor=2222;
		int owner=0;
		int lock=0;
		InetAddress group;
		MulticastSocket socket;
		byte[] buf;
		String[] parts;
		
		System.out.println("Port for process:"+porto);
		start(portstart);
		System.out.println("Clock starts:");
	
		for(int i=0;i!=20;i++)
		{
			System.out.println("Clock:"+i);
			try {
				if(i==5)
				{
				send(porto,portcoor,porto+":Permission?");
				group = InetAddress.getByName("228.5.6.10");
				socket = new MulticastSocket(porto);
				socket.joinGroup(group);
				buf = new byte[100];
				DatagramPacket recv = new DatagramPacket(buf, buf.length);
				socket.receive(recv);
				String s = new String(buf,recv.getOffset(),recv.getLength());
				System.out.println(s);
				parts = s.split(Pattern.quote(":"));
				if(parts[1].equals("yes"))
				{
					Thread.sleep(5000);
					Counter c=null;
					try {
				         FileInputStream fileIn = new FileInputStream("G:\\file.txt");
				         ObjectInputStream in = new ObjectInputStream(fileIn);
				         c = (Counter) in.readObject();
				         in.close();
				         fileIn.close();
						c.value=""+i;
						c.owner=""+porto;
				      }catch(Exception ex) {
				       
				      }

					try {
				         FileOutputStream fileOut =
				         new FileOutputStream("G:\\file.txt");
				         ObjectOutputStream out = new ObjectOutputStream(fileOut);
				         out.writeObject(c);
				         out.close();
				         fileOut.close();
				         System.out.println("G:\\file.txt");
				         
				      }catch(Exception e) {
				         
				      }
					Thread.sleep(1000);
					send(porto,portcoor,porto+":done");
					
					System.out.println("Sent done.");
				}
				else
				{
					System.out.println("Didn't get permission");
				}
		}
				
				
				if(i==18)
				{
				send(porto,portcoor,porto+":Permission?");
				group = InetAddress.getByName("228.5.6.10");
				socket = new MulticastSocket(porto);
				socket.joinGroup(group);
				buf = new byte[100];
				DatagramPacket recv = new DatagramPacket(buf, buf.length);
				socket.receive(recv);
				String s = new String(buf,recv.getOffset(),recv.getLength());
				System.out.println(s);
				parts = s.split(Pattern.quote(":"));
				if(parts[1].equals("yes"))
				{
					Counter c=null;
					try {
				         FileInputStream fileIn = new FileInputStream("G:\\file.txt");
				         ObjectInputStream in = new ObjectInputStream(fileIn);
				         c = (Counter) in.readObject();
				         in.close();
				         fileIn.close();
						c.value=""+i;
				      }catch(Exception ex) {
				       
				      }

					try {
				         FileOutputStream fileOut =
				         new FileOutputStream("G:\\file.txt");
				         ObjectOutputStream out = new ObjectOutputStream(fileOut);
				         out.writeObject(c);
				         out.close();
				         fileOut.close();
				         System.out.println("Data is saved in G:\\file.txt");
				         Thread.sleep(2000);
				         send(porto,portcoor,porto+":done");
				      }catch(Exception e) {
				         
				      }
				}
				else
				{
					System.out.println("Didn't get permission");
				}
				}
				
				
				
				
				
				
				
				
				Thread.sleep(1000);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
		}
		
		
		System.out.print("done");
		System.exit(0);

	}

}
