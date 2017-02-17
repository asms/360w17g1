/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Park;

/**
 * Test suite for {@link Park}.
 * 
 * <p>Getters are not tested because this is whitebox testing.</p>
 * 
 * @author Steven Smith
 * @version 1.0
 */
public class ParkTest {
	
	/**
	 * Park object to use for testing.
	 */
	private Park myPark;
	
	/**
	 * Default park name to use for park test object.
	 */
	private static final String PARK_NAME = "Cherry Parkes";
	
	/**
	 * Default park location to use for park test location.
	 */
	private static final String PARK_LOCATION = "1900 Commerce St, Tacoma, WA 98402";
	
	/**
	 * Setup method initializes test park object. 
	 * Simultaneously tests the constructor.
	 */
	@Before
	public void setup() {
		myPark = new Park(PARK_NAME, PARK_LOCATION);
	}
	
	/**
	 * Test method for {@link Park#Park()} 
	 * Expects a NullPointerException for a null name parameter.
	 */
	@Test(expected = NullPointerException.class)
	public void testConstructorNullPointerExceptionForNullName() {
		new Park(null, PARK_LOCATION);
	}
	
	/**
	 * Test method for {@link Park#Park()} 
	 * Expects a NullPointerException for a null location parameter.
	 */
	@Test(expected = NullPointerException.class)
	public void testConstructorNullPointerExceptionForNullLocation() {
		new Park(PARK_NAME, null);
	}
	
	/**
	 * Test method for {@link Park#getName()} 
	 */
	@Test
	public void testGetName() {
		assertEquals(PARK_NAME, myPark.getName());
	}
	
	/**
	 * Test method for {@link Park#getLocation()} 
	 */
	@Test
	public void testGetLocation() {
		assertEquals(PARK_LOCATION, myPark.getLocation());
	}
	
	/**
	 * Test method for {@link Park#toString()} 
	 * Checks for expected output.
	 */
	@Test
	public void testToString() {
		final String x = "Cherry Parkes, 1900 Commerce St, Tacoma, WA 98402";
		assertEquals(x, myPark.getName() + ", " + myPark.getLocation() );
	}
	
	/**
	 * Test method for {@link Park#equals()} 
	 * Checks if different park objects with identical values are equal.
	 */
	@Test
	public void testEqualsTrue() {
		final Park x = new Park(new String(PARK_NAME), new String(PARK_LOCATION));
		assertEquals(x, myPark);
	}
	
	/**
	 * Test method for {@link Park#equals()} 
	 * Checks if different park objects with different names are not equal.
	 */
	@Test
	public void testEqualsFalseDifferentName() {
		final Park x = new Park("Park Name", new String(PARK_LOCATION));
		assertNotEquals(x, myPark);
	}
	
	/**
	 * Test method for {@link Park#equals()} 
	 * Checks if different park objects with different locations are not equal.
	 */
	@Test
	public void testEqualsFalseDifferentLocation() {
		final Park x = new Park(new String(PARK_NAME), "1234 56th Street, City, State 78910");
		assertNotEquals(x, myPark);
	}
	
	/**
	 * Test method for {@link Park#equals()} 
	 * Checks if a null parameter yields false.
	 */
	@Test
	public void testEqualsNotNull() {
		assertFalse(myPark.equals(null));
	}
	
	/**
	 * Test method for {@link Park#equals()} 
	 * Checks if a parmeter of a different object type yields false.
	 */
	@Test
	public void testEqualsDifferentObject() {
		assertFalse(myPark.equals(PARK_NAME));
	}
	
	/**
	 * Test method for {@link Park#hashCode()} 
	 * Checks if parks with identical fields yield the same hash codes.
	 */
	@Test
	public void testHashCodeTrue() {
		final int x = new Park(new String(PARK_NAME), new String(PARK_LOCATION)).hashCode();
		assertEquals(x, myPark.hashCode());
	}
	
	/**
	 * Test method for {@link Park#hashCode()} 
	 * Checks if parks with different names yield different hash codes.
	 */
	@Test
	public void testHashCodeFalseDifferentName() {
		final int x = new Park("Park Name", new String(PARK_LOCATION)).hashCode();
		assertNotEquals(x, myPark.hashCode());
	}
	
	/**
	 * Test method for {@link Park#hashCode()} 
	 * Checks if parks with different locations yield different hash codes.
	 */
	@Test
	public void testHashCodeFalseDifferentLocation() {
		final int x = new Park(new String(PARK_NAME), "1234 56th Street, City, State 78910").hashCode();
		assertNotEquals(x, myPark.hashCode());
	}

}
