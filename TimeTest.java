import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class TimeTest {

	@Test
	void test() {
		Time time = new Time();
		
		// expect the boolean to return false 
		// boolean should return false unless this test program is run at 02:02am 
		assertEquals(false, time.rightTimeToText("02:02", "6178988989"));
	}
}
