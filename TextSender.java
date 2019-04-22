/*
 * @author Pammi
 */
public interface TextSender {
	
	// sends the reply to the phoneNumber via tezt
	public void sendText(String phoneNumber, MBTAReply reply);
}
