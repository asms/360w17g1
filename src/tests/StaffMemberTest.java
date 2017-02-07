package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.StaffMember; 

public class StaffMemberTest {
	
	/**
	 * The volunteer used for testing.
	 */
	private Volunteer myStaffMember;
	
	private static final String USERNAME = "staffmember1";
	
	@Before
	public void setUp() throws Exception {
		myVolunteer = new Volunteer(USERNAME);
	}

	@Test
	public void testOnUserName() {
		assertEquals(USERNAME, myStaffMember.getUserName());
	}

}
