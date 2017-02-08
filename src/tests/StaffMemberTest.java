package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.Park;
import model.StaffMember;
import model.Volunteer;
import view.StaffMemberView;

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
		StaffMemberView.staffMember = myStaffMember;
	}

	@Test
	public void testOnUserName() {
		assertEquals(USERNAME, myStaffMember.getUserName());
	}

	@Test
	public void testOnPreviousJobs() {
		StaffMemberView.viewPastJobs();
	}
}
