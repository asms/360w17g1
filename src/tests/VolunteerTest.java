package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Volunteer;

public class VolunteerTest {
	
	/**
	 * The volunteer used for testing.
	 */
	private Volunteer myVolunteer;
	
	private static final String USERNAME = "eli";
	private static final String FIRST_NAME = "Eli";
	private static final String LAST_NAME = "Ile";
	private static final String PHONE_NUMBER = "253-123-4567";
	private static final String EMAIL_ADDRESS= "eli@gmail.com";
	
	@Before
	public void setUp() throws Exception {
		myVolunteer = new Volunteer(USERNAME, FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS);
	}

	@Test
	public void testOnUserName() {
		assertEquals(USERNAME, myVolunteer.getUserName());
	}

}
