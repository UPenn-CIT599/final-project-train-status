import java.util.ArrayList;

/*
 * @author Pammi
 */
public interface TextSender {
	
	// sends the reply to the phoneNumber via text
	public void sendText(String phoneNumber, ArrayList<MBTAReply> reply);
}
