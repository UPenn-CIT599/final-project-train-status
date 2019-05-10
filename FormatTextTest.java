import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class FormatTextTest {
	
	@Test
	void testNull() {
		FormatText ft = new FormatText();
		ArrayList<MBTAReply> replies = new ArrayList<>();
		String result = ft.textToSend(replies);
		String expected = "No predictions available!";
		assertEquals(result, expected);
	}
	
	@Test
	void testEndStation() {
		FormatText ft = new FormatText();
		ArrayList<MBTAReply> replies = new ArrayList<>();
		MBTAReply reply1 = new MBTAReply("Red", "place-alfcl", "0", null, "13:05");
		MBTAReply reply2 = new MBTAReply("Red", "place-alfcl", "0", null, "13:09");
		MBTAReply reply3 = new MBTAReply("Red", "place-alfcl", "0", null, "13:15");
		replies.add(reply1);
		replies.add(reply2);
		replies.add(reply3);
		
		String result = ft.textToSend(replies);
		String expected = "The upcoming trains will depart from the end station Alewife at the following times: 13:05 13:09 13:15";
		assertEquals(result, expected);
	}
	
	@Test
	void testEndStationNoDeparturePrediction() {
		FormatText ft = new FormatText();
		ArrayList<MBTAReply> replies = new ArrayList<>();
		MBTAReply reply1 = new MBTAReply("Red", "place-alfcl", "0", null, null);
		replies.add(reply1);
		
		String result = ft.textToSend(replies);
		String expected = "No predictions available!";
		assertEquals(result, expected);
	}
	
	@Test
	void testNonEndStation() {
		FormatText ft = new FormatText();
		ArrayList<MBTAReply> replies = new ArrayList<>();
		MBTAReply reply1 = new MBTAReply("Orange", "place-dwnxg", "0", "14:04", null);
		MBTAReply reply2 = new MBTAReply("Orange", "place-dwnxg", "0", "14:09", null);
		MBTAReply reply3 = new MBTAReply("Orange", "place-dwnxg", "0", "14:21", null);
		replies.add(reply1);
		replies.add(reply2);
		replies.add(reply3);
		String result = ft.textToSend(replies);
		String expected = "The upcoming trains will arrive at the Downtown Crossing station at the following times, "
				+ "going in the Forest Hills direction: 14:04 14:09 14:21";
		assertEquals(result, expected);
	}
	
	@Test
	void testNonEndStationNoArrivalPredictions() {
		FormatText ft = new FormatText();
		ArrayList<MBTAReply> replies = new ArrayList<>();
		MBTAReply reply1 = new MBTAReply("Orange", "place-dwnxg", "0", null, null);
		replies.add(reply1);
		String result = ft.textToSend(replies);
		String expected = "No predictions available!";
		assertEquals(result, expected);
	}
	
	
	
}
