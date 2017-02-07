package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VolunteerTest {
	
	/**
	 * The volunteer used for testing.
	 */
	private Volunteer myVolunteer;
	
	private static final String USERNAME = "volunteer1";
	
	@Before
	public void setUp() throws Exception {
		myVolunteer = new Volunteer(USERNAME);
	}

	@Test
	public void testOnUserName() {
		assertEquals(USERNAME, myVolunteer.getUserName());
	}

}
