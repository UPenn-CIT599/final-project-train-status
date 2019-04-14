import java.util.ArrayList;

public class Request {

	String line;
	String start;
	String destination;
	ArrayList<Integer> weekdays;
	String arrivalTime;
	String textTime;
	
	public Request (String line, String start, String destination, ArrayList<Integer> weekdays, String arrivalTime, String textTime) {
		this.line = line;
		this.start = start;
		this.destination = destination;
		for (int i = 0; i < weekdays.size(); i++) {
			this.weekdays.add(weekdays.get(i), i);
		}
		this.arrivalTime = arrivalTime;
		this.textTime = textTime;
	}
	
	
	
}
