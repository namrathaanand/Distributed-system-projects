package Assignment2b;

public class Node2 {

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

		int porto=4679;
		int ports=5678;
		int portot=5555;
		int portstart=1234;
		
		receive(porto);
		start(portstart);
		
		for(int i=0;i!=5;i++)
		{
			send(porto,porto,"Node2:"+i);
//			System.out.println("Node2:"+i);
			send(porto,ports,"Node2:"+i);	
			send(porto,portot,"Node2:"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		System.out.print("done");
		System.exit(0);
	}
}