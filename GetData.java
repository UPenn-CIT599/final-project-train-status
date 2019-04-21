/*
 * @author Pammi
 * This interface is created so that a Junit test is possible
 */
public interface GetData {

	// this method takes the criteria from the Request r and asks the MBTA website for the information needed
	// it then store the downloaded info from MBTA into a MBTAReply object and returns it
	public MBTAReply crawlMBTA(Request r);
}
