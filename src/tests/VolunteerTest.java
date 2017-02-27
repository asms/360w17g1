package tests;

/**
 * @author Bryce
 * @version 2.0
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.Job.WorkDuty;
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
	private Job myJob;
	
	private static final String USERNAME = "eli";
	private static final String FIRST_NAME = "Eli";
	private static final String LAST_NAME = "Ile";
	private static final String PHONE_NUMBER = "253-123-4567";
	private static final String EMAIL_ADDRESS= "eli@gmail.com";
	
	@Before
	public void setUp() throws Exception {
		myVolunteer = new Volunteer(USERNAME, FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS);
		JobDate jd = new JobDate(); 
		jd.addDays(3);
		myJob = new Job(new ParkManager("Larry"), "Some string", new Park("Name", "location"), jd, jd, jd, "Some string", 1, 3, 3);
	}

	@Test
	public void testAddJob_ValidJob_True() {
		
//		myVolunteer.addJob(myJob);
//		myJob.addVolunteer(myVolunteer, WorkDuty.HEAVY);
//		System.out.println(myVolunteer.getPendingJobs());
//		System.out.println(myJob.getVolunteers().contains(myVolunteer));
//		System.out.println(myVolunteer.getPendingJobs().contains(myJob));
//		System.out.println(myVolunteer.getPendingJobs().isEmpty());
//		assertTrue(myVolunteer.getPendingJobs().contains(myJob));
//		
//	}

    
	}

}
