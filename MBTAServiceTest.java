import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/** @author Pammi Yeung
 *  This is the Junit test class
 *  First test checks that the addEntry method works in the GUI class
 *  Second test checks that the 
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
				replies.add(new MBTAReply("Red", "place-alfcl", "0", null, "13:05"));
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
		  
		MBTAService service = new MBTAService(getData, textSender);
		Request req = new Request("Red", "place-davis", "0", "08:05");
		
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
		// because the send() method checks if the current time is 08:05
		// unless this MBTAServiceTest program is being run at exactly 08:05,
		// the timeToText boolean will be false and the crawlMBTA and sendText methods will not be called
		
		//expectedSentHash.put("6171234567",new MBTAReply("Red", "place-alfcl", "0", null, "13:05"));
		
		// expect the crawlMBTA and sendText methods to not be called 
		assertEquals(expectedSentHash, sentTexts);
		
		
	}

}
