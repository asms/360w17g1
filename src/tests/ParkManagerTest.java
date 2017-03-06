/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.ParkManager;

/**
 * @author Steven Smith
 * @version 1.0
 * <p>Getters are not tested because this is whitebox testing.</p>
 */
public class ParkManagerTest {
	
	/** The park manager used for testing. */
	private ParkManager myParkManager;
	
	private static final String USERNAME = "saintjimmy";

	@Before
	public void setUp() {
		myParkManager = new ParkManager(USERNAME);
	}
	
	// TODO: Not needed.
	@Test
	public void getUsernameTest() {
		assertEquals(USERNAME, myParkManager.getUserName());
	}
		
}
