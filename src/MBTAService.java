
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

/**
 * 
 * @author Pammi
 * This class maintains the state of the program. It contains a hashtable that has all the requests.
 * It has the methods used to add and remove entries to and from the hashtable
 * It also calls the methods of the GetData class to turn each request in 
 * the hashtable into a MBTAReply object, and then used the TextSender class' method to sends text messages 
 * to the user
 * 
 */
public class MBTAService extends ScheduledService<Void> {

	private final HashMap<String, Request> req; // holds all the phone numbers and requests
	private GetData getData; 
	private TextSender textSender;
	private Time time = new Time();
	 
	public MBTAService(GetData getData, TextSender textSender) {
		req = new HashMap<String, Request>();
		this.getData = getData;
		this.textSender = textSender;
	}
	
	public void addEntry(String phoneNumber, Request request) {      
	    req.put(phoneNumber, request);
	    
	}
	
	public void removeEntry(String ph) {
		req.remove(ph);
	}
	
	// send text message
	public void send() {
		for (Entry<String, Request> entry : req.entrySet()) {
			
			String phoneNumber = entry.getKey();
		    Request request = entry.getValue();
		    String userTextTime = request.getTextTime();
		    boolean timeToText = time.rightTimeToText(userTextTime, phoneNumber);
		    if (timeToText) {
			    ArrayList<MBTAReply> replies = getData.crawlMBTA(request);
			    textSender.sendText(phoneNumber, replies);
		    }
		}
	}
	
	public HashMap<String, Request> getHash(){
		return req;
	}
	
	
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try {
					send();
				} catch (Exception e) {
					e.printStackTrace();
				}		
				return null;
			}	
		};
	}

}
