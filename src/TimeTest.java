import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Pammi Yeung
 * JUnit tests for the Time class
 *
 */

public class TimeTest {

	@Test
	void testTrue() {
		Time time = new Time();
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		String currentTime = Integer.toString(hour) + ":" + Integer.toString(minute);
		// got current time to compare to the Time object's current time so expect true
		assertEquals(true, time.rightTimeToText(currentTime, "6171234567"));
	}
	
	@Test
	void testFalse() {
		Time time = new Time();
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE)+5;
		String currentTime = Integer.toString(hour) + ":" + Integer.toString(minute);
		// added 5 minutes to the current time so expect false
		assertEquals(false, time.rightTimeToText(currentTime, "6171234567"));
	}
}
