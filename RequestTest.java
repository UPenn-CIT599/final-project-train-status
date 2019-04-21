package application;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

class RequestTest {

    List<String> list = Arrays.asList("1", "2");
    HashMap<String, Request> test = new HashMap<String, Request>();
    
    
    private Request r;
    @Before
    public void setup() { 
	r = new Request("red", "Princeton", "Philadelphia", list,"9am","830am");
		
    }
        

    @Test
    public void testRequestNotNull() {
		
	assertNotNull("Request is not null", r);
    }
    
    @Test
    public void testRequestNull() {
		
	assertNull("Request is null", r);
    }
    
    @Test
    public void testaddEntry() {
	r.addEntry("12313", r);	
	test.put("1213",r);
	assertThat(r.containsKey("12313"));
	r.equals(test);
    }

}
