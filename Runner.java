import java.util.Map;

public class Runner {
	
	// runner 
	public static void main(String[] args) throws InterruptedException {
		GUI gui = new GUI();
		gui.setVisible(true);
		while (true) {
			Map<String, Request> allRequests = gui.getRequests(); 
			// turn every request from the hashmap into a MBTAReply object
			
			// pause for 1 minute then start from the loop again
			Thread.sleep(60000);
		}
	}
	
}
