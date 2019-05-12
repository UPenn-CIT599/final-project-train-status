
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/** 
 * @author Pammi Yeung
 * This class is a data structure that holds translation between:
 * 1) direction code (0 or 1) and end station names (see directionDecode HashMap)
 * 2) station id and the station name (see stationDecode)
 */


public class StationMap {

	HashMap<String, Tuple<Tuple<String, String>, Tuple<String, String>>> directionDecode ;
	HashMap<String, String> stationDecode;

	public HashMap<String, Tuple<Tuple<String, String>, Tuple<String, String>>> getDirectionDecode() {
		return directionDecode;
	}
	
	public HashMap<String, String> getStationDecode() {
		return stationDecode;
	}
	
	public StationMap() {
		directionDecode = new HashMap<>();
		stationDecode = new HashMap<>();

		// directionDecode is hardcoded 
		directionDecode.put("Red", new Tuple(new Tuple("0","Ashmont/Braintree"), new Tuple("1", "Alewife")));
		directionDecode.put("Mattapan", new Tuple(new Tuple("0","Mattapan"), new Tuple("1", "Ashmont")));
		directionDecode.put("Orange", new Tuple(new Tuple("0","Forest Hills"), new Tuple("1", "Oak Grove")));
		directionDecode.put("Green-B", new Tuple(new Tuple("0","Boston College"), new Tuple("1", "Park Street")));
		directionDecode.put("Green-C", new Tuple(new Tuple("0","Cleveland Circle"), new Tuple("1", "North Station")));
		directionDecode.put("Green-D", new Tuple(new Tuple("0","Riverside"), new Tuple("1", "Government Center")));
		directionDecode.put("Green-E", new Tuple(new Tuple("0","Heath Street"), new Tuple("1", "Lechmere")));
		directionDecode.put("Blue", new Tuple(new Tuple("0","Bowdoin"), new Tuple("1", "Wonderland")));

		// stationDecode is filled in using an API call
		createStationDecode("red");
		createStationDecode("blue");
		createStationDecode("orange");

	}

	// parse the info returned by API call, and put into stationDecode 
	private void createStationDecode(String s) {
		String response = getIDs(s);
		//create a JSON object with the String response
		JSONObject jObj = new JSONObject(response);

		//Look at the raw String response
		//Look for the "data" key
		//After the colon there is a square bracket indicating a JSONArray
		JSONArray jArray = jObj.getJSONArray("data");

		//loop through each of these JSON objects and the values corresponding 
		//to the different things that appear before the colon.
		for(int i=0;i<jArray.length();i++) {
			// Each item in jArray is a JSONObject 
			JSONObject innerJSON = jArray.getJSONObject(i);
			if (!innerJSON.has("attributes")) {
				continue;
			}
			
			JSONObject attributes = innerJSON.getJSONObject("attributes");

			String stationName = attributes.getString("name");

			String stationID = innerJSON.getString("id");
			stationDecode.put(stationID,  stationName);

		}
		
	}
	
	// takes line as parameter and crawl MBTA to get station names and ids
	private String getIDs(String line) {
		
		String result = new String();
		File f = new File("./data/"+line+"-line.json");
		
		try {
			Scanner s = new Scanner(f);
			while (s.hasNext()) {
				result += s.nextLine();
			}
			s.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return result;
		
	}
	
	
}
