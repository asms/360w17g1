/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Park;
import model.ParkManager;

/**
 * Test Suite for User class.
 * 
 * @author Mohamed Mohamed
 * @author Amy I
 * @author Bryce
 * @version 1.0
 */
public class AbstractUserTest
{
	
	public String USERNAME = "ba2012";
	public Park PARK_1 = new Park("Cherry Park", "1234 N 56th");
	
	
	
    /**
     * User object to user for testing.
     */
   ParkManager myUser;
   


    /**
     * Tests constructor and sets up user object.
     */
    @Before
    public void setUp()
    {
        myUser = new ParkManager(USERNAME);
    }



    /**
      Test method for {@link model.AbstractUser#toString()}.
    */
    @Test
    public void testToString_TwoStringsAreEqual_ExpectedTrue()
    {
        assertEquals(USERNAME + ", ParkManager", myUser.getUserName() + ", " + myUser.getClass().getSimpleName());
    }
    

    /**
     * Test method for {@link model.AbstractUser#equals(java.lang.Object)}.
     */
    @Test
    public void testEquals_EqualObject_expectedTrue()
    {
    	assertEquals(myUser, myUser);
    	assertEquals(myUser.getClass().getSimpleName(), myUser.getClass().getSimpleName());
       
    }
    
    /**
     * Test method for {@link model.AbstractUser#equals(java.lang.Object)}.
     */
    @Test
    public void testEquals_notEqualUser_ExpectedFalse() {
    	
    	assertFalse(myUser.equals(new ParkManager("Larry")));
    }

    /**
     * Test method for {@link model.AbstractUser#equals(java.lang.Object)}.
     */
    @Test
    public void testEquals_notEqualObject_ExpectedFalse() {
    	
    	assertFalse(myUser.equals(new Park("Park", "Location")));
    }
    
    @Test
    public void testHashCode_EqualObjects_ExpectedTrue() {
    	
    	assertEquals(myUser.hashCode(), myUser.hashCode());
    }
 
    
    @Test
    public void testHashCode_NotEqualObjects_ExpectedFalse() {
    	
    	assertTrue(myUser.hashCode() != (new ParkManager("Jerry").hashCode()));
    	
    	
    }
}
