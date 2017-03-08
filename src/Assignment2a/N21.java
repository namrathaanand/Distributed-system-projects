package Assignment2a;


public class N21 {

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

		
		int ack1=6666;
		int ack2=6667;
		int ack3=6668;
		

		NodeReceiver t1 = new NodeReceiver(porto);	 
		t1.start();
		
		ACKReceiver r1 = new ACKReceiver(ack1);	 
		r1.start();
		
		start(portstart);

			send(porto,porto,"10:Message from Node 1");

			send(porto,ports,"10:Message from Node 1");
			send(porto,portot,"10:Message from Node 1");
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				Thread.sleep(12000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Acknowledgements received:");	
			for (int i = 0; i < r1.Buffer.size(); i++) {
				System.out.println(r1.Buffer.get(i));
			}
			System.out.println("The ordered message sequence is:");

			for (int i = 0; i < t1.Buffer.size(); i++) {
				System.out.println(t1.Buffer.get(i));
			}

	

		System.exit(0);

	}

}
