/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import model.ParkManager;
import model.Job;
import model.Park;

/**
 * @author Steven Smith
 * @version 1.0
 * <p>Getters are not tested because this is whitebox testing.</p>
 */
public class ParkManagerTest {
	
	/**
	 * The park manager used for testing.
	 */
	private ParkManager myParkManager;
	
	private static final String USERNAME = "user0001";

	@Before
	public void setUp() {
		myParkManager = new ParkManager(USERNAME);
	}
	
	@Test
	public void getUsernameTest() {
		assertEquals(USERNAME, myParkManager.getUserName());
	}
	
	/**
	 * Test method for {@link ParkManager#associate(Park thePark)}
	 */
	@Test
	public void testAssociate() {
		HashMap<String, Park> expected = new HashMap<String, Park>();
		expected = new ParkManager(USERNAME).getAssociatedParks();
		HashMap<String, Park> actual = new HashMap<String, Park>();
		actual = myParkManager.getAssociatedParks();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * Test method for {@link ParkManager#getAssociatedParks()}
	 */
	@Test
	public void testGetAssociatedParks() {		
		HashMap<String, Park> expected = new HashMap<String, Park>();
		for (int i = 0; i < 100; i++) {
			expected = new ParkManager(USERNAME).getAssociatedParks();
		}
		
		HashMap<String, Park> actual = new HashMap<String, Park>();
		for (int i = 0; i < 100; i++) {
			actual = myParkManager.getAssociatedParks();
		}
			
		assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link ParkManager#getJobs()}
	 */
	@Test
	public void testGetJobs() {	
		final ArrayList<Job> expectedJobs = new ArrayList<Job>();
		for (int i = 0; i < 100; i++) {
			expectedJobs.addAll(new ParkManager(USERNAME).getJobs());
		}
		
		final ArrayList<Job> actualJobs = new ArrayList<Job>(); 
			actualJobs.addAll(myParkManager.getJobs());
			
		assertEquals(expectedJobs, actualJobs);
	}
		
}
