package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.JobController;
import model.JobDate;
import model.Park;
import model.ParkManager;
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
	
	/**
     * Test method for {@link model.Volunteer#getFirstName()}.
     */
    @Test
    public void testGetFirstName() {
    	assertEquals(FIRST_NAME, myVolunteer.getFirstName());
    }
    
    /**
     * Test method for {@link model.Volunteer#getLastName()}.
     */
    @Test
    public void testGetLastName() {
    	assertEquals(LAST_NAME, myVolunteer.getLastName());
    }
    
    /**
     * Test method for {@link model.Volunteer#getPhoneNumber()}.
     */
    @Test
    public void testGetPhoneNumber() {
    	assertEquals(PHONE_NUMBER, myVolunteer.getPhoneNumber());
    }
    
    /**
     * Test method for {@link model.Volunteer#getEmailAddress()}.
     */
    @Test
    public void testGetEmailAddress() {
    	assertEquals(EMAIL_ADDRESS, myVolunteer.getEmailAddress());
    }
    
    @Test
    public void testIfNonBlackballedCanSignUp() {
    	final Volunteer volunteer = new Volunteer("smithsd", "Steve", "Smith", "XXX-XXX-XXXX", "xxxxxxx@uw.edu");
    	final JobController jobController = new JobController();
    	jobController.clear();
    	Job job;
    	try {
			job = new Job(new ParkManager("asdf"),
					"Cleaning up litter", new Park("Sunset", "location"),
					new JobDate().addDays(4),
					new JobDate().setFromTimeString("10:00 am"),
					new JobDate().setFromTimeString("2:00 pm"),
					"Cleaning up after yesterday's little league tournament.", 2, 2,
					2);
			volunteer.setBlackballedFlag(false);
	    	jobController.assertSigningUp(volunteer, job);
		} catch (ParseException e) {
			fail(e.getMessage());
		}
    }
    
    @Test(expected = IllegalStateException.class)
    public void testIfBlackballedCannotSignUp() {
    	final Volunteer volunteer = new Volunteer("smithsd", "Steve", "Smith", "XXX-XXX-XXXX", "xxxxxxx@uw.edu");
    	final JobController jobController = new JobController();
    	jobController.clear();
    	Job job;
		try {
			job = new Job(new ParkManager("asdf"), "Cleaning up litter", new Park("Sunset", "location"), new JobDate().addDays(4), new JobDate().setFromTimeString("10:00 am"),
					new JobDate().setFromTimeString("2:00 pm"), "Cleaning up after yesterday's little league tournament.", 2, 2,
					2);
			volunteer.setBlackballedFlag(true);
	    	jobController.assertSigningUp(volunteer, job);
		} catch (ParseException e) {
			fail(e.getMessage());
		}
    	
    }
    
    /**
     * Test method for {@link model.Volunteer#toString()}.
     */
    @Test
    public void testToString() {
    	String expected = FIRST_NAME + " " + LAST_NAME + " " + PHONE_NUMBER + " " + EMAIL_ADDRESS;
    	assertEquals(expected, myVolunteer.getFirstName() + " " + myVolunteer.getLastName() + " " + myVolunteer.getPhoneNumber() + " " + myVolunteer.getEmailAddress());
    }
    
    

}
