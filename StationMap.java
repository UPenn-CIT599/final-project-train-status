import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class StationMap {

	HashMap<String, Tuple<Tuple<String, String>, Tuple<String, String>>> directionDecode ;
	HashMap<String, String> stationDecode;
	private char[] stationName;

	public HashMap<String, Tuple<Tuple<String, String>, Tuple<String, String>>> getDirectionDecode() {
		return directionDecode;
	}
	
	public HashMap<String, String> getStationDecode() {
		return stationDecode;
	}
	
	public StationMap() {
		directionDecode = new HashMap<>();
		stationDecode = new HashMap<>();

		
		directionDecode.put("Red", new Tuple(new Tuple("0","Ashmont/Braintree"), new Tuple("1", "Alewife")));
		directionDecode.put("Mattapan", new Tuple(new Tuple("0","Mattapan"), new Tuple("1", "Ashmont")));
		directionDecode.put("Orange", new Tuple(new Tuple("0","Forest Hills"), new Tuple("1", "Oak Grove")));
		directionDecode.put("Green-B", new Tuple(new Tuple("0","Boston College"), new Tuple("1", "Park Street")));
		directionDecode.put("Green-C", new Tuple(new Tuple("0","Cleveland Circle"), new Tuple("1", "North Station")));
		directionDecode.put("Green-D", new Tuple(new Tuple("0","Riverside"), new Tuple("1", "Government Center")));
		directionDecode.put("Green-E", new Tuple(new Tuple("0","Heath Street"), new Tuple("1", "Lechmere")));
		directionDecode.put("Blue", new Tuple(new Tuple("0","Bowdoin"), new Tuple("1", "Wonderland")));

		
		createStationDecode("Red");
		createStationDecode("Blue");
		createStationDecode("Orange");

	}

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
	
	private String getIDs(String line) {
		URL urlObj;
		URLConnection yc;
		BufferedReader in;
		
		String fullURL = "https://api-v3.mbta.com/stops?filter[route]="+line;

		try {
			urlObj = new URL(fullURL);
			yc = urlObj.openConnection();
			in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();

		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("Invalid url: " + fullURL, e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
