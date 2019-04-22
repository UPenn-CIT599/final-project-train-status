import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

/*
 *  This is the Junit test class
 *  First test checks that the addEntry method works in the GUI class
 *  Second test checks that the 
 */

class MBTAServiceTest {

	@Test
	void test() {
		
		HashMap<String, MBTAReply> sentTexts = new HashMap();
		
		// implement test versions of GetData and TextSender interfaces
		  GetData getData = new GetData() {

			@Override
			public MBTAReply crawlMBTA(Request r) {
				return new MBTAReply("Red", "South Station", "Alewife", "8am");
			}
			  
		  };
		  
		  TextSender textSender = new TextSender() {

			@Override
			public void sendText(String phoneNumber, MBTAReply reply) {
				// add phone number and reply to check what was sent
				sentTexts.put(phoneNumber, reply);
			}
			  
		  };
		  
		MBTAService service = new MBTAService(getData, textSender);
		Request req = new Request("Red", "South Station", "Alewife", "8am", "730am");
		service.addEntry("6171234567",req);
		HashMap<String, Request> modifiedHash = service.getHash();
		
		// create the expected Hash
		HashMap<String,Request> expectedHash = new HashMap<>();
		expectedHash.put("6171234567", req);
		// compare them
		assertEquals(expectedHash, modifiedHash);
		
		service.send();
		
		// create the expected Hash
		HashMap<String,MBTAReply> expectedSentHash = new HashMap<>();
		expectedSentHash.put("6171234567",new MBTAReply("Red", "South Station", "Alewife", "8am"));
		assertEquals(expectedSentHash, sentTexts);
		
		
	}

}
