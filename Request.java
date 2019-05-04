/*
 * @author Pammi
 * This class is a data structure used to hold information about the request,
 * such as the line, the starting station, the destination station, the arrivalTime (e.g. 8am, when the user wants to board the train at the station)
 * and textTime (e.g. 7.40am, if the user needs 20 minutes to walk from home to the starting station)
 */

public class Request {

	private String line;
	private String station;
	private String direction;
	private String textTime;
	
	public Request (String line, String station, String direction, String textTime) {
		this.line = line;
		this.station = station;
		this.direction = direction;
		this.textTime = textTime;
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

	public String getTextTime() {
		return textTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		result = prime * result + ((station == null) ? 0 : station.hashCode());
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
		if (textTime == null) {
			if (other.textTime != null)
				return false;
		} else if (!textTime.equals(other.textTime))
			return false;
		return true;
	}

}
