import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

/** @author Pammi Yeung
 *  This is the Junit test class for the MBTAService class
 *  
 *  First test checks that the addEntry method works in the GUI class
 *  
 *  Second test checks that when the send() method is called, crawlMBTA and 
 *  the boolean that indicates whether it is time to send a text are working correctly
 */

class MBTAServiceTest {

	@Test
	void test() {
		
		HashMap<String, MBTAReply> sentTexts = new HashMap<>();
		
		// implement test versions of GetData and TextSender interfaces
		  GetData getData = new GetData() {

			@Override
			public ArrayList<MBTAReply> crawlMBTA(Request r) {
				ArrayList<MBTAReply> replies = new ArrayList<>();
				replies.add(new MBTAReply("Red", "place-davis", "0", null, "13:05"));
				return replies;
			}
			  
		  };
		  
		  TextSender textSender = new TextSender() {
			@Override
			public void sendText(String phoneNumber, ArrayList<MBTAReply> reply) {
				// add phone number and reply to check what was sent
				sentTexts.put(phoneNumber, reply.get(0));
			}
			  
		  };
		  
		  Calendar now = Calendar.getInstance();
			int hour = now.get(Calendar.HOUR_OF_DAY);
			int minute = now.get(Calendar.MINUTE);
			String currentTime = Integer.toString(hour) + ":" + Integer.toString(minute);
			
		MBTAService service = new MBTAService(getData, textSender);
		Request req = new Request("Red", "place-davis", "0", currentTime);
		
		// call the addEntry method
		service.addEntry("6171234567",req);
		HashMap<String, Request> modifiedHash = service.getHash();
		
		// create the expected Hash
		HashMap<String,Request> expectedHash = new HashMap<>();
		expectedHash.put("6171234567", req);
		
		// First test for service: compare the expectedHash to modifiedHash
		assertEquals(expectedHash, modifiedHash);
		
		// call the send method
		service.send();
		
		// create the expected Hash
		HashMap<String,MBTAReply> expectedSentHash = new HashMap<>();
		
		// line below is commented out tom keep exepectedSentHash null
		// because the send() method checks if the current time is 03:05
		// unless this MBTAServiceTest program is being run at exactly 03:05,
		// the timeToText boolean will be false and the crawlMBTA and sendText methods will not be called
		
		
		expectedSentHash.put("6171234567",new MBTAReply("Red", "place-davis", "0", null, "13:05"));
		
		// expect the crawlMBTA and sendText methods to not be called 
		assertEquals(expectedSentHash, sentTexts);
		
		
	}

}
