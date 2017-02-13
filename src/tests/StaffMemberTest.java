package tests;

import static org.junit.Assert.assertEquals;

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
		Job job = new Job("theName", new Park("Park Name", "Park Location"), new Date(), new Date(), new Date(), "theDescription", 1,2,3);
		myStaffMember.addPastJobs(job);

	}

	@Test
	public void testOnUserName() {
		assertEquals(USERNAME, myStaffMember.getUserName());
	}

	@Test
	public void testOnPreviousJobs() {
		System.out.println("TEST!");
		Job job = new Job("theName", new Park("Park Name", "Park Location"), new Date(), new Date(), new Date(), "theDescription", 1,2,3);
		System.out.println(myStaffMember.getPastJobs());
		System.out.println(job);
		assertEquals(job, myStaffMember.getPastJobs());

		System.out.println("TEST");
	}
}
