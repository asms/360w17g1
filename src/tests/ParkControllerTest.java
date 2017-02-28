/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.ParkController;
import model.Park;

/**
 * Test suite for {@link ParkController}.
 * @author Steven Smith
 * @author Trinh Nguyen
 * @version 1.0
 */
public class ParkControllerTest {
    
    /**
     * ParkController objects used for testing.
     */
    private ParkController myParkController;
    
    /**
     * Default park name to use for park test object.
     */
    private static final String PARK_NAME = "Park Name 50";
    
    /**
     * Default park location to use for park test location.
     */
    private static final String PARK_LOCATION = "Park Location 50";
    
    @Before
    public void setup() {
        myParkController = new ParkController();
        myParkController.clear();
    }
    
    @After
    public void cleanup() {
        myParkController.clear();
    }
    
    /**
     * Test method for {@link ParkController#getParkByName(String)}
     * Checks if a parks is retrievable by name.
     */
    @Test
    public void testGetParkByName_SingleParkNameParkLocation_ExpectedTrue() {
        final Park expectedPark = new Park(PARK_NAME, PARK_LOCATION);
        myParkController.addPark(expectedPark);
        final Park park = myParkController.getParkByName(expectedPark.getName());
        assertEquals(expectedPark, park);
    }
    
    /**
     * Test method for {@link ParkController#getParkByName(String)}
     * Checks if a parks is retrievable by name after many have been added.
     */
    @Test
    public void testGetPark_ArrayOfParkNamesParkLocations_ExpectedTrue() {
        for (int i = 0; i < 100; i++) {
            myParkController.addPark(new Park("Park Name " + i, "Park Location " + i));
        }
        
        final Park expectedPark = new Park(PARK_NAME, PARK_LOCATION);
        final Park park = myParkController.getParkByName(expectedPark.getName());
        assertEquals(expectedPark, park);
    }
    
    /**
     * Test method for {@link ParkController#getAllParks(Park)}
     * Checks if empty array is returned if no parks are available.
     */
    @Test
    public void testGetAllParks_EmptyArrayOfParkNameParkLocation_ExpectedTrue() {
        final ArrayList<Park> parks = myParkController.getAllParks();
        assertTrue(parks.isEmpty());
    }
    
    /**
     * Test method for {@link ParkController#getAllParks(Park)}
     * Checks if all parks are added to and retrievable from the controller.
     */
    @Test
    public void testGetAllParks_ArrayOfParkNamesAndParkLocations_ExpectedTrue() {
        final ArrayList<Park> parks = new ArrayList<Park>();
        for (int i = 0; i < 100; i++) {
            parks.add(new Park("Park Name " + i, "Park Location " + i));
            myParkController.addPark(new Park("Park Name " + i, "Park Location " + i));
        }
        final ArrayList<Park> allParks = myParkController.getAllParks();
        assertTrue(allParks.containsAll(parks) && parks.containsAll(allParks));
    }
    
}
