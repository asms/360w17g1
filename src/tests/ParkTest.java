/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.JobDateTime;
import model.Park;
import model.ParkManager;

/**
 * Test suite for {@link Park}.
 * <p>Getters are not tested because this is whitebox testing.</p>
 * @author Steven Smith
 * @author Trinh Nguyen
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
    
    @Before
    public void setup() {
    	myPark = new Park(PARK_NAME, PARK_LOCATION);
    }
    
    /**
     * Test method for {@link Park#Park()}
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorNullPointerException_NullParkName() {
        new Park(null, PARK_LOCATION);
    }
    
    /**
     * Test method for {@link Park#Park()}
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorNullPointerException_NullParkLocation() {
        new Park(PARK_NAME, null);
    }
    
    /**
     * Test method for {@link Park#equals()}
     * Checks if different park objects with identical values are equal.
     */
    @Test
    public void testEquals_SamParkNameSameParkLocation_ExpectedTrue() {
        final Park expected = new Park(new String(PARK_NAME), new String(PARK_LOCATION));
        assertEquals(expected, myPark);
    }
    
    /**
     * Test method for {@link Park#equals()}
     * Checks if different park objects with different park names are not equal.
     */
    @Test
    public void testEquals_DifferentParkName_ExpectedFalse() {
        final Park expected = new Park("Park Name", new String(PARK_LOCATION));
        assertNotEquals(expected, myPark);
    }
    
    /**
     * Test method for {@link Park#equals()}
     * Checks if different park objects with different locations are not equal.
     */
    @Test
    public void testEquals_DifferentParkLocation_ExpectedFalse() {
        final Park expected = new Park(new String(PARK_NAME), "1234 56th Street, City, State 78910");
        assertNotEquals(expected, myPark);
    }
    
    /**
     * Test method for {@link Park#equals()}
     */
    @Test
    public void testEquals_NullObject_ExpectedFalse() {
        assertFalse(myPark.equals(null));
    }
    
    /**
     * Test method for {@link Park#equals()}
     */
    @Test
    public void testEquals_DifferentObject_ExpectedFalse() {
        assertFalse(myPark.equals(PARK_NAME));
    }
    
    /**
     * Test method for {@link Park#hashCode()}
     */
    @Test
    public void testHashCode_IdenticalParkNameLocation_ExpectedSameHashCode() {
        final int expected = new Park(new String(PARK_NAME), new String(PARK_LOCATION)).hashCode();
        assertEquals(expected, myPark.hashCode());
    }
    
    /**
     * Test method for {@link Park#hashCode()}
     */
    @Test
    public void testHashCode_DifferentParkName_ExpectedDifferentHashCode() {
        final int expected = new Park("Park Name", new String(PARK_LOCATION)).hashCode();
        assertNotEquals(expected, myPark.hashCode());
    }
    
    /**
     * Test method for {@link Park#hashCode()} 
     */
    @Test
    public void testHashCode_DifferentParkLocation_ExpectedDifferentHashCode() {
        final int expected = new Park(new String(PARK_NAME), "1234 56th Street, City, State 78910").hashCode();
        assertNotEquals(expected, myPark.hashCode());
    }
    
    
}
