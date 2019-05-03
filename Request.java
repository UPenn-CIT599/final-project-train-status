/*
 * @author Pammi
 * This class is a data structure used to hold information about the request,
 * such as the line, the starting station, the destination station, the arrivalTime (e.g. 8am, when the user wants to board the train at the station)
 * and textTime (e.g. 7.40am, if the user needs 20 minutes to walk from home to the starting station)
 */

public class Request {

	String line;
	String start;
	String destination;
	//String arrivalTime;
	String textTime;
	
	public Request (String line, String start, String destination,  String textTime) {
		this.line = line;
		this.start = start;
		this.destination = destination;
		//this.arrivalTime = arrivalTime;
		this.textTime = textTime;
	}

	// implemented so that the JUnit test knows how to tell if two Request objects are the same
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((textTime == null) ? 0 : textTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (arrivalTime == null) {
			if (other.arrivalTime != null)
				return false;
		} else if (!arrivalTime.equals(other.arrivalTime))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (line == null) {
			if (other.line != null)
				return false;
		} else if (!line.equals(other.line))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (textTime == null) {
			if (other.textTime != null)
				return false;
		} else if (!textTime.equals(other.textTime))
			return false;
		return true;
	}

}
