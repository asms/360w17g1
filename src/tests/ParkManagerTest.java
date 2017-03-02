/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.ParkManager;
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
		Set<Park> expected = new HashSet<Park>();
		expected = new ParkManager(USERNAME).getAssociatedParks();
		Set<Park> actual = new HashSet<Park>();
		actual = myParkManager.getAssociatedParks();
		
		assertEquals(expected, actual);	
	}
	
	/**
	 * Test method for {@link ParkManager#getAssociatedParks()}
	 */
	@Test
	public void testGetAssociatedParks() {		
		Set<Park> expected = new HashSet<Park>();
		for (int i = 0; i < 100; i++) {
			expected = new ParkManager(USERNAME).getAssociatedParks();
		}
		
		Set<Park> actual = new HashSet<Park>();
		for (int i = 0; i < 100; i++) {
			actual = myParkManager.getAssociatedParks();
		}
			
		assertEquals(expected, actual);
	}
		
}
