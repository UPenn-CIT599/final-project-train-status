import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Pammi Yeung
 * JUnit tests for the FormatText class
 *
 */

class FormatTextTest {
	
	@Test
	void testNull() {
		FormatText ft = new FormatText();
		ArrayList<MBTAReply> replies = new ArrayList<>();
		String result = ft.textToSend(replies);
		String expected = "No predictions available!";
		assertEquals(expected, result);
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
		String expected = "The upcoming trains will depart from the end station Alewife at the following times: 13:05 13:09 13:15 ";
		assertEquals(expected, result);
	}
	
	@Test
	void testEndStationNoDeparturePrediction() {
		FormatText ft = new FormatText();
		ArrayList<MBTAReply> replies = new ArrayList<>();
		MBTAReply reply1 = new MBTAReply("Red", "place-alfcl", "0", null, null);
		replies.add(reply1);
		
		String result = ft.textToSend(replies);
		String expected = "No predictions available!";
		assertEquals(expected, result);
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
				+ "going in the Forest Hills direction: 14:04 14:09 14:21 ";
		assertEquals(expected, result);
	}
	
	@Test
	void testNonEndStationMoreThanFiveArrivals() {
		FormatText ft = new FormatText();
		ArrayList<MBTAReply> replies = new ArrayList<>();
		MBTAReply reply1 = new MBTAReply("Orange", "place-dwnxg", "0", "14:04", null);
		MBTAReply reply2 = new MBTAReply("Orange", "place-dwnxg", "0", "14:09", null);
		MBTAReply reply3 = new MBTAReply("Orange", "place-dwnxg", "0", "14:21", null);
		MBTAReply reply4 = new MBTAReply("Orange", "place-dwnxg", "0", "14:31", null);
		MBTAReply reply5 = new MBTAReply("Orange", "place-dwnxg", "0", "14:36", null);
		MBTAReply reply6 = new MBTAReply("Orange", "place-dwnxg", "0", "14:39", null);
		replies.add(reply1);
		replies.add(reply2);
		replies.add(reply3);
		replies.add(reply4);
		replies.add(reply5);
		replies.add(reply6);
		String result = ft.textToSend(replies);
		String expected = "The upcoming trains will arrive at the Downtown Crossing station at the following times, "
				+ "going in the Forest Hills direction: 14:04 14:09 14:21 14:31 14:36 ";
		assertEquals(expected, result);
	}
	
	@Test
	void testNonEndStationBothArrivalDeparture() {
		FormatText ft = new FormatText();
		ArrayList<MBTAReply> replies = new ArrayList<>();
		MBTAReply reply1 = new MBTAReply("Orange", "place-dwnxg", "0", "14:04", "14:05");
		MBTAReply reply2 = new MBTAReply("Orange", "place-dwnxg", "0", "14:09", null);
		MBTAReply reply3 = new MBTAReply("Orange", "place-dwnxg", "0", "14:21", null);
		replies.add(reply1);
		replies.add(reply2);
		replies.add(reply3);
		String result = ft.textToSend(replies);
		String expected = "The upcoming trains will arrive at the Downtown Crossing station at the following times, "
				+ "going in the Forest Hills direction: 14:04 14:09 14:21 ";
		assertEquals(expected, result);
	}
	
	@Test
	void testNonEndStationNoArrivalPredictions() {
		FormatText ft = new FormatText();
		ArrayList<MBTAReply> replies = new ArrayList<>();
		MBTAReply reply1 = new MBTAReply("Orange", "place-dwnxg", "0", null, "20:08");
		replies.add(reply1);
		String result = ft.textToSend(replies);
		String expected = "No predictions avaialble at this point, the last train just left the " + "Downtown Crossing" + " station at " + "20:08" 
		+ ", going in the " + "Forest Hills" + " direction";
		assertEquals(expected, result);
	}
	
	@Test
	void testNonEndStationNoArrivalNoDeparturePredictions() {
		FormatText ft = new FormatText();
		ArrayList<MBTAReply> replies = new ArrayList<>();
		MBTAReply reply1 = new MBTAReply("Orange", "place-dwnxg", "0", null, null);
		replies.add(reply1);
		String result = ft.textToSend(replies);
		String expected = "No predictions available!";
		assertEquals(expected, result);
	}

	
	
	
}
