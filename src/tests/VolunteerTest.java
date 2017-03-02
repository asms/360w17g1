package tests;

/**
 * @author Bryce
 * @version 2.0
 * getters not tested
 */

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import model.Job;


import model.JobDateTime;

import model.Park;
import model.ParkManager;
import model.Volunteer;

public class VolunteerTest {
	
	/**
	 * The volunteer used for testing.
	 */
	private Volunteer myVolunteer;
	private Job myJob;
	
	private static final String USERNAME = "eli";
	private static final String FIRST_NAME = "Eli";
	private static final String LAST_NAME = "Ile";
	private static final String PHONE_NUMBER = "253-123-4567";
	private static final String EMAIL_ADDRESS= "eli@gmail.com";
	
	@Before
	public void setUp() throws Exception {
		myVolunteer = new Volunteer(USERNAME, FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS);
		JobDateTime jd = new JobDateTime(); 
		jd.addDays(3);
		myJob = new Job(new ParkManager("Larry"), "Some string", new Park("Name", "location"), jd, jd, jd, jd, "Some string", 1, 3, 3);
	}

	/**
	 * test method for addJob {@link model.Volunteer#addJob(Job)}
	 */
	@Test
	public void testAddJob_NonNullJob_True() {
		
		myVolunteer.addJob(myJob);
		assertTrue(myVolunteer.myJobs.contains(myJob));    
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddJob_NonValidJob() {
		myJob = null;
		myVolunteer.addJob(myJob);
	}

}
