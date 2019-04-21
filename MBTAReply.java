/*
 * @author Pammi
 * This class is a data structure used to hold information about the response from calling MBTA's API functions
 * including the line, the starting station, the destination station, the arrivalTime (e.g. 8am, when the user wants to board the train at the station)
 * and textTime (e.g. 7.40am, if the user needs 20 minutes to walk from home to the starting station)
 */


public class MBTAReply {
	String line;
	String start;
	String direction;
	String predictedArrivalTime;
	
	public MBTAReply(String line, String start, String direction, String predictedArrivalTime) {
		this.line = line;
		this.start = start;
		this.direction = direction;
		this.predictedArrivalTime = predictedArrivalTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		result = prime * result + ((predictedArrivalTime == null) ? 0 : predictedArrivalTime.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}
	
	// implemented so that the Junit test know how to tell if two MBTAReply objects are identical
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MBTAReply other = (MBTAReply) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (line == null) {
			if (other.line != null)
				return false;
		} else if (!line.equals(other.line))
			return false;
		if (predictedArrivalTime == null) {
			if (other.predictedArrivalTime != null)
				return false;
		} else if (!predictedArrivalTime.equals(other.predictedArrivalTime))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
	
	
}
