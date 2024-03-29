import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *  @author Pammi Yeung
 * This class implements the GetData interface 
 * Contains methods to call MBTA's API and return information in the form of MBTAReply
 */

public class GetDataImpl implements GetData {
	
	URL urlObj;
	URLConnection yc;
	BufferedReader in;
	
	// https://api-v3.mbta.com//predictions?filter[route]=Red&filter[stop]=place-asmnl&filter[direction_id]=0&sort=arrival_time
	@Override
	public ArrayList<MBTAReply> crawlMBTA(Request r) {
		String response = getResponse(r);
		//create a JSON object with the String response
		JSONObject jObj = new JSONObject(response);
		
		//Look at the raw String response
		//Look for the "data" key
		//After the colon there is a square bracket indicating a JSONArray
		JSONArray jArray = jObj.getJSONArray("data");
		ArrayList<MBTAReply> MBTAReplies = new ArrayList<>();
		
		//loop through each of these JSON objects and the values corresponding 
		//to the different things that appear before the colon.
		for(int i=0;i<jArray.length();i++) {
			// Each item in jArray is a JSONObject 
			JSONObject innerJSON = jArray.getJSONObject(i);
			
			if (!innerJSON.has("attributes")) {
				continue;
			}
			JSONObject attributes = innerJSON.getJSONObject("attributes");
			
			String arrivalTime = attributes.optString("arrival_time");
			arrivalTime = arrivalTime.isEmpty() ? null : arrivalTime.substring(11, 16);
			
			String departureTime = attributes.optString("departure_time");
			departureTime = departureTime.isEmpty() ? null : departureTime.substring(11,16);
			
			MBTAReply reply= new MBTAReply(r.getLine(), r.getStation(), r.getDirection(), arrivalTime, departureTime);
			MBTAReplies.add(reply);
		}
		return MBTAReplies;

	}
	
	// crawl the API of MBTA and return a String response
	private String getResponse(Request r) {
		String urlLine = r.getLine();
		String urlStation = r.getStation();
		String urlDirection = r.getDirection();
		String sortCriterion = endStop(urlStation)? "arrival_time" : "departure_time";
		String fullURL = String.format("https://api-v3.mbta.com//predictions?filter[route]=%s&filter[stop]=%s&filter[direction_id]=%s&sort="+sortCriterion,
			urlLine, urlStation, urlDirection);
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
	
	// determine if the station is an end station
	private boolean endStop(String station) {
		return station == "place-forhl" || station == "place-ogmnl" || station == "place-bomnl" || station == "place-wondl" || 
				station == "place-alfcl" || station == "place-asmnl";
	}

}
