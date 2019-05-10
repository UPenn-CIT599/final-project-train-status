import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;


class StationMapTest {

	@Test
	void test1(){
		StationMap sMap = new StationMap();
		HashMap<String, Tuple<Tuple<String, String>, Tuple<String, String>>> directionDecode = sMap.getDirectionDecode();
		String direction = directionDecode.get("Red").first.second;
		String expectedDirection = "Ashmont/Braintree";
		assertEquals(direction, expectedDirection);
	}
	
	@Test
	void test2(){
		StationMap sMap = new StationMap();
		HashMap<String, Tuple<Tuple<String, String>, Tuple<String, String>>> directionDecode = sMap.getDirectionDecode();
		String direction = directionDecode.get("Orange").second.second;
		String expectedDirection = "Oak Grove";
		assertEquals(direction, expectedDirection);
	}
	
	
	@Test
	void test3(){
		StationMap sMap = new StationMap();
		HashMap<String, String> stationDecode = sMap.getStationDecode();
		String stationName = stationDecode.get("place-bomnl");
		String expected= "Bowdoin";
		assertEquals(stationName, expected);
	}
	
	@Test
	void test4(){
		StationMap sMap = new StationMap();
		HashMap<String, String> stationDecode = sMap.getStationDecode();
		String stationName = stationDecode.get("place-gover");
		String expected= "Government Center";
		assertEquals(stationName, expected);
		
	}
}
