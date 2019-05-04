import java.util.ArrayList;
import java.util.HashMap;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class TextSenderImpl implements TextSender {

	private final String accountSid;
	private final String authToken; 
	private final StationMap map = new StationMap();
	
	public TextSenderImpl() {
		accountSid = System.getenv("TWILIO_ACCOUNT_SID");
		authToken = System.getenv("TWILIO_AUTH_TOKEN");
	}

	@Override
	public void sendText(String phoneNumber, ArrayList<MBTAReply> replies) {

		Twilio.init(accountSid, authToken);
		
		String text = textToSend(replies);
		
		Message message = Message.creator(
				new PhoneNumber(phoneNumber),
				new PhoneNumber("+12019925372"), // Account's number from Twilio
				text).create();

		System.out.println(message.getSid());
	}
	
	private String textToSend(ArrayList<MBTAReply> replies) {
		
		// if the API call did not return and information
		if (replies == null) {
			return "No predictions available at this time!";
		}
		
		String toSend = "";
		HashMap<String, Tuple<Tuple<String, String>, Tuple<String, String>>> directionDecode = map.getDirectionDecode();
		HashMap<String, String> stationDecode = map.getStationDecode();
		
		// look at the first reply to get the station and direction
		MBTAReply firstReply = replies.get(0);
		String station = firstReply.getStation();
		String stationDecoded = stationDecode.get(station);
		
		// this is a tuple that looks like Tuple(Tuple("0","Ashmont/Braintree"),Tuple("1", "Alewife"));
		Tuple<Tuple<String, String>, Tuple<String, String>> directionTuple = directionDecode.get(replies.get(0).getLine());
		String directionDecoded = firstReply.getDirection() == "0"? directionTuple.first.second: directionTuple.second.second;
		
		// go thru all replies and put the arrival time and departure time into arraylists
		// if all arrivalTimes are null, but at least one departure time: 
		// show the last departure time and say no predictions yet
		// if all arrivalTimes and departureTimes are null. 
		ArrayList<String> arrivals = new ArrayList<>();
		ArrayList<String> departures = new ArrayList<>();
		for (MBTAReply r: replies) {
			String arrivalTime = r.getArrival();
			String departureTime = r.getDeparture();
			if (arrivalTime != "null") {
				arrivals.add(arrivalTime);
			}
			if (departureTime != "null") {
				departures.add(departureTime);
			}
		}
		
		// if the station is an end station, there will never be arrival times, use departure times instead
		if (endStop(station)) {
			if (departures.size() == 0) {
				toSend = "No predictions available at this time!";
			}
			else {
				toSend = "The upcoming trains will depart from the end station "+ stationDecoded + " at the following times:";
			
				for (int i = 0; i < Math.min(departures.size(), 5); i++) {
					toSend += departures.get(i);
					toSend += " ";
				}
			}
		}
		
		// if the station is not an end station, return at most 5 arrival times if not empty
		else if (arrivals.size()> 0 ) {
			toSend = "The upcoming trains will arrive at the " + stationDecoded + " station at the following times, going in the " 
						+ directionDecoded + "direction: ";
			for (int i = 0; i < Math.min(arrivals.size(), 5); i++) {
				toSend += arrivals.get(i);
				toSend += " ";
			}
		}
		
		// if the station is not an end station, but arrivals is empty, look at departures and say when the last train departed
		else if (arrivals.size()==0 && departures.size()>0) {
			toSend = "No predictions avaialble at this point, the last train just left the " + stationDecoded + " at " + departures.get(0) 
			+ ", going in the " + directionDecoded + "direction: ";
		}
		
		return toSend;
	}

	private boolean endStop(String station) {
		return station == "place-forhl" || station == "place-ogmnl" || station == "place-bomnl" || station == "place-wondl" || 
				station == "place-alfcl" || station == "place-asmnl";
	}

}
