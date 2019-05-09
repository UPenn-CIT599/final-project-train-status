import java.util.ArrayList;
import java.util.HashMap;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * 
 * @author Pammi Yeung
 * implements the TextSender interface
 * this class has a method that takes a phone number and a list of MBTAReply and
 * send text messages to the user using Twilio's built-in functions
 */

public class TextSenderImpl implements TextSender {

	private final String accountSid;
	private final String authToken; 
	private FormatText format;
	
	public TextSenderImpl() {
		// for security purpose, the log in info is stored as system variables instead of 
		// explicitly in the code as String
		accountSid = System.getenv("TWILIO_ACCOUNT_SID");
		authToken = System.getenv("TWILIO_AUTH_TOKEN");
		Twilio.init(accountSid, authToken);
		format = new FormatText();
	}

	@Override
	public void sendText(String phoneNumber, ArrayList<MBTAReply> replies) {
		String text = format.textToSend(replies);
		
		// Twilio API's built in code
		Message message = Message.creator(
				new PhoneNumber(phoneNumber),
				new PhoneNumber("+12019925372"), // Account's number from Twilio
				text).create();
	}


}
