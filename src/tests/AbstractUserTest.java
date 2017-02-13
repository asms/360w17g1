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
     * Test method for {@link model.AbstractUser#getUserName()}.
     */
    @Test
    public void testGetUserByUserName_ba2012()
    {
        assertEquals(USERNAME, myUser.getUserName());
    }

    
   /**
     * Test method for {@link model.AbstractUser#getOptions()}.
   */
    
    @Test
    public void testGetUserByUserName_false()
    {
    	String noString = null;
    	String wrongName = "wrong";
    	
        assertFalse(myUser.getUserName().equals(noString));
        assertFalse(myUser.getUserName().equals(wrongName));
    }

    /**
     * Test method for {@link model.User#login(java.lang.String)}.
     */
//    @Test
//    public void testLogin()
//    {
//        fail("Not yet implemented");
//    }
	/**
	 *  Test method for {@link model.AbstractUser#getFirstName()}.
	 */


    /**
     * Test method for {@link model.AbstractUser#getKey()}.
     */
    @Test
    public void testGetKey()
    {
        assertEquals(USERNAME, myUser.getKey());
    }

    /**
      Test method for {@link model.AbstractUser#toString()}.
    */
    @Test
    public void testToString()
    {
        assertEquals(USERNAME + ", ParkManager", myUser.toString());
    }
    

    /**
     * Test method for {@link model.AbstractUser#equals(java.lang.Object)}.
     */
    @Test
    public void testEquals_EqualObject()
    {
    	assertEquals(myUser, myUser);
    	assertEquals(myUser.getClass().getSimpleName(), myUser.getClass().getSimpleName());
       
    }
    
    @Test
    public void testEquals_notEqualObject() {
    	
    	assertFalse(myUser.equals(new ParkManager("Larry")));
    }

    
    @Test
    public void testHashCode_Equal() {
    	
    	assertEquals(myUser.hashCode(), myUser.hashCode());
    }
    
    @Test
    public void testHashCode_NotEqual() {
    	
    	assertTrue(myUser.hashCode() != (new ParkManager("Jerry").hashCode()));
    	
    	
    }
}
