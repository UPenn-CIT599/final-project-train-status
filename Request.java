package application;

import java.util.ArrayList;
import java.util.List;

public class Request {

	String line;
	String start;
	String destination;
	List<String> weekdays;
	String arrivalTime;
	String textTime;
	static HashMap<String, Request> req = new HashMap<String, Request>();
	
	public Request (String line, String start, String destination, List<String> items, String arrivalTime, String textTime) {
		this.line = line;
		this.start = start;
		this.destination = destination;
		this.weekdays=items;
		this.arrivalTime = arrivalTime;
		this.textTime = textTime;
	}
	
	HashMap<String, Request> addEntry(String ph,Request r) {
	      
	    req.put(ph, r);
	    
	    return req;
	
	
}
