package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.Park;
import model.StaffMember;

public class StaffMemberTest {

	/**
	 * The volunteer used for testing.
	 */
	private StaffMember myStaffMember;

	private static final String USERNAME = "staffmember1";

	@Before
	public void setUp() throws Exception {
		myStaffMember = new StaffMember(USERNAME);
		Job job = new Job("theName", new Park("Park Name", "Park Location"), new Date(), new Date(), new Date(),
				"theDescription", 1, 2, 3);
		myStaffMember.addPastJobs(job);

	}

	@Test
	public void testOnUserName_InputUserName_GetSameUsername() {
		assertEquals(USERNAME, myStaffMember.getUserName());
	}

	@Test
	public void testOnPreviousJobs_InputPastJob_GetPastJob() {
		Job job = new Job("theName", new Park("Park Name", "Park Location"), new Date(), new Date(), new Date(),
				"theDescription", 1, 2, 3);
		assertTrue(myStaffMember.getPastJobs().contains(job));
	}
}
