import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
	
	public boolean rightTimeToText(String userTime) {
		return compareDates(userTime) && !sentAlready(userTime);
	}
	
	private boolean sentAlready(String userTextTime) {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
	    int month = now.get(Calendar.MONTH);
	    int day = now.get(Calendar.DAY_OF_MONTH);
	    String timeDate = userTextTime+" "+year+" "+month+" "+day;
	    
	    // check and remember whether we have sent at the requested time for today
	    boolean result = log.contains(timeDate);
	    // update the log to show that we have sent the text
	    log.add(timeDate);

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
	
	public static void main(String[] args) {
		Time t = new Time();
		t.compareDates("16:49");
	}
}