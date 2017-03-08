package Bonus;


public class NodeInit {

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

		int porto=8888;
		int portstart=1234;
		String s1="he";
		if(send(porto,portstart,s1)==1)
		{
			System.out.println("Sent Successfully.");
		}
		else{
			System.out.println("Send failure.");
		}

	}
}