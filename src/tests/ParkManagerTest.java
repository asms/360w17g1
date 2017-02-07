/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import model.Park;
import model.ParkManager;

/**
 * @author Steven Smith
 * @version 1.0
 */
public class ParkManagerTest {
	
	/**
	 * The park manager used for testing.
	 */
	private ParkManager myParkManager;
	
	private static final String USERNAME = "user0001";
	private static final Park PARK_1 = new Park("Cherry Park", "1234 N 56th");
	private static final HashSet<Park> ASSOCIATED_PARKS = new HashSet<Park>(Arrays.asList(PARK_1));

	@Before
	public void setUp() {
		myParkManager = new ParkManager(USERNAME, ASSOCIATED_PARKS);
	}
	
	@Test
	public void getUsernameTest() {
		assertEquals(USERNAME, myParkManager.getUserName());
	}

	@Test
	public void getAssociatedParksTest() {
		assertTrue(myParkManager.getAssociatedParks().containsAll((HashSet<Park>) ASSOCIATED_PARKS.clone()));
	}

}
