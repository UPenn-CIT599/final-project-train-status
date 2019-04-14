import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

// this class contains the text fields required to show the user what they need to provide
// there are two kind of requests, start and stop
// to start, a user needs to provide line, start station, destination station,
// preferred departure time from the starting station, preferred time and weekdays for receiving the texts, 
// and phone number for receiving texts
public class GUI extends JFrame {
	
	private final Map<String, Request> requests = new HashMap<>();
	JTextField startPhoneNumberField;
	JTextField stopPhoneNUmberField;
	// and other fields...
	
	public GUI () {
		JButton button = new JButton("PUSH ME!");
		startPhoneNumberField = new JTextField(20);
		stopPhoneNUmberField = new JTextField(20);
		
		button.addActionListener(action -> addEntry());
		Container contentPane = this.getContentPane();
		contentPane.add(button);
		contentPane.add(startPhoneNumberField);
		
	}
	
	

	// instance variables for text fields and buttons
	
	// some sort of listeners
	
	// when the start button is pressed, grabs the values from the text fields and store them into a newly created Request object
	// put the Request object into the HashMap passed in, with the phone number as the key
	
	public Map<String, Request> getRequests() {
		return requests;
	}
	
	private void addEntry() {
		String phoneNumber = startPhoneNumberField.getText();
		ArrayList<Integer> weekdays = new ArrayList<>();
		Request placeHolder = new Request("line", "start", "destination", weekdays, "arrivalTime", "textTime");
		requests.put(phoneNumber, placeHolder);
		
	}
	
	// when the stop button is pressed, removes the Request associated with the phoneNumber key
	private void removeEntry() {
		String phoneNumber = stopPhoneNUmberField.getText();
		requests.remove(phoneNumber);
	}
}
