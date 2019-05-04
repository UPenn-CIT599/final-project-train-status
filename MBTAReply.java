/*
 * @author Pammi
 * This class is a data structure used to hold information about the response from calling MBTA's API functions
 * including the line, the starting station, the destination station, the arrivalTime (e.g. 8am, when the user wants to board the train at the station)
 * and textTime (e.g. 7.40am, if the user needs 20 minutes to walk from home to the starting station)
 */


public class MBTAReply {
	String line;
	String station;
	String direction;
	String arrival;
	String departure;
	
	public MBTAReply(String line, String station, String direction, String arrivalTime, String departureTime) {
		this.line = line;
		this.station = station;
		this.direction = direction;
		this.arrival = arrivalTime;
		this.departure = departureTime;
	}

	public String getLine() {
		return line;
	}

	public String getStation() {
		return station;
	}

	public String getDirection() {
		return direction;
	}

	public String getArrival() {
		return arrival;
	}

	public String getDeparture() {
		return departure;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrival == null) ? 0 : arrival.hashCode());
		result = prime * result + ((departure == null) ? 0 : departure.hashCode());
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		result = prime * result + ((station == null) ? 0 : station.hashCode());
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
		MBTAReply other = (MBTAReply) obj;
		if (arrival == null) {
			if (other.arrival != null)
				return false;
		} else if (!arrival.equals(other.arrival))
			return false;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
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
		if (station == null) {
			if (other.station != null)
				return false;
		} else if (!station.equals(other.station))
			return false;
		return true;
	}
	
	
}
