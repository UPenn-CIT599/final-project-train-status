import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/** 
 * @author Pammi Yeung
 * This class serves two functions:
 * 1) check if the current time is the time specified by the user to receive a text
 * This is done in compareDates
 * 2) keep a log of whether a text has been sent for a given time/day/month/year/phone number combination
 * This is done in sentAlready
 */

public class Time{

	public static final String inputFormat = "HH:mm";
	public static ArrayList<String> log;
	private Date date;
	private Date dateCompareOne;
	private Date dateCompareTwo;
	
	public Time() {
		log = new ArrayList<>();
	}
	
	SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
	
	public boolean rightTimeToText(String userTime, String phoneNumber) {
		return compareDates(userTime) && !sentAlready(userTime, phoneNumber);
	}
	
	private boolean sentAlready(String userTextTime, String phoneNumber) {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
	    int month = now.get(Calendar.MONTH);
	    int day = now.get(Calendar.DAY_OF_MONTH);
	    String timeDate = userTextTime+" "+year+" "+month+" "+day;
	    
	    // check and remember whether we have sent at the requested time for today
	    boolean result = log.contains(timeDate+phoneNumber);
	    // update the log to show that we have sent the text
	    log.add(timeDate+phoneNumber);

	    return result;
	}
	
	private boolean compareDates(String userTextTime){
	    Calendar now = Calendar.getInstance();
	
	    int hour = now.get(Calendar.HOUR_OF_DAY);
	    int minute = now.get(Calendar.MINUTE);
	
	    date = parseDate(hour + ":" + minute);
	    Date textTime = parseDate(userTextTime);
	    Calendar c = Calendar.getInstance(); 
	    
	    c.setTime(textTime); 
	    c.add(Calendar.MINUTE, -2);
	    dateCompareOne = c.getTime();
	    
	    c.setTime(textTime); 
	    c.add(Calendar.MINUTE, 2);
	    dateCompareTwo = c.getTime();
	
	    return ( dateCompareOne.before( date ) && dateCompareTwo.after(date));
	}
	
	private Date parseDate(String date) {
	
	    try {
	        return inputParser.parse(date);
	    } catch (java.text.ParseException e) {
	        return new Date(0);
	    }
	}

}