/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Park;
import model.ParkController;

/**
 * Test suite for {@link ParkController}.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public class ParkControllerTest {
	
	/**
	 * ParkController objects used for testing.
	 */
	private ParkController myParkController;

	@Before
	public void setup() {
		myParkController = new ParkController();
	}
	
	/**
	 * Test method for {@link ParkController#getParkByName(String)} checks if a parks is retrievable by name.
	 */
	@Test
	public void testGetParkByName() {
		final Park expectedPark = new Park("Park Name 50", "Park Location 50");
		myParkController.addPark(expectedPark);
		final Park park = myParkController.getParkByName(expectedPark.getName());
		assertEquals(expectedPark, park);
	}
	
	/**
	 * Test method for {@link ParkController#getParkByName(String)} checks if a parks is retrievable by name after many
	 * have been added.
	 */
	@Test
	public void testGetParkByNameFromMany() {
		for (int i = 0; i < 100; i++) {
			myParkController.addPark(new Park("Park Name " + i, "Park Location " + i));
		}
		
		final Park expectedPark = new Park("Park Name 50", "Park Location 50");
		final Park park = myParkController.getParkByName(expectedPark.getName());
		assertEquals(expectedPark, park);
	}
	
	/**
	 * Test method for {@link ParkController#getAllParks(Park)} checks if empty array is returned if no parks are
	 * available.
	 */
	@Test
	public void testGetAllParksWhenEmpty() {
		final ArrayList<Park> parks = myParkController.getAllParks();
		assertTrue(parks.isEmpty()); // No exception is thrown and list is empty
	}
	
	/**
	 * Test method for {@link ParkController#getAllParks(Park)} checks if all parks are added to and retrievable from
	 * the controller.
	 */
	@Test
	public void testGetAllParks() {
		final ArrayList<Park> parks = new ArrayList<Park>();
		for (int i = 0; i < 100; i++) {
			parks.add(new Park("Park Name " + i, "Park Location " + i));
			myParkController.addPark(new Park("Park Name " + i, "Park Location " + i));
		}
		final ArrayList<Park> allParks = myParkController.getAllParks();
		assertTrue(allParks.containsAll(parks) && parks.containsAll(allParks));
	}

}
