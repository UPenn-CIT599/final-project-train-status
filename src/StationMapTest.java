import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Pammi Yeung
 * JUnit tests for the StationMap class
 *
 */

class StationMapTest {

	@Test
	void testDirectionRed(){
		StationMap sMap = new StationMap();
		HashMap<String, Tuple<Tuple<String, String>, Tuple<String, String>>> directionDecode = sMap.getDirectionDecode();
		String direction = directionDecode.get("Red").first.second;
		String expectedDirection = "Ashmont/Braintree";
		assertEquals(expectedDirection,direction);
	}
	
	@Test
	void testDirectionOrange(){
		StationMap sMap = new StationMap();
		HashMap<String, Tuple<Tuple<String, String>, Tuple<String, String>>> directionDecode = sMap.getDirectionDecode();
		String direction = directionDecode.get("Orange").second.second;
		String expectedDirection = "Oak Grove";
		assertEquals(expectedDirection,direction);
	}
	
	
	@Test
	void testDirectionBlue(){
		StationMap sMap = new StationMap();
		HashMap<String, Tuple<Tuple<String, String>, Tuple<String, String>>> directionDecode = sMap.getDirectionDecode();
		String direction = directionDecode.get("Blue").second.second;
		String expectedDirection = "Wonderland";
		assertEquals(expectedDirection,direction);
	}
	
	@Test
	void testStationOrange(){
		StationMap sMap = new StationMap();
		HashMap<String, String> stationDecode = sMap.getStationDecode();
		String stationName = stationDecode.get("place-chncl");
		String expected= "Chinatown";
		assertEquals(expected,stationName);
	}
	
	@Test
	void testStaionRed(){
		StationMap sMap = new StationMap();
		HashMap<String, String> stationDecode = sMap.getStationDecode();
		String stationName = stationDecode.get("place-davis");
		String expected= "Davis";
		assertEquals(expected, stationName);
		
	}
	

	@Test
	void testStaionBlue(){
		StationMap sMap = new StationMap();
		HashMap<String, String> stationDecode = sMap.getStationDecode();
		String stationName = stationDecode.get("place-aqucl");
		String expected= "Aquarium";
		assertEquals(expected, stationName);
		
	}
}
