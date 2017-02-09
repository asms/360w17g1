/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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

	@Before
	public void setUp() {
		myParkManager = new ParkManager(USERNAME);
	}
	
	@Test
	public void getUsernameTest() {
		assertEquals(USERNAME, myParkManager.getUserName());
	}
	
	//TODO: implement more test methods

}
