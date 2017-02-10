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
 * @version 1.0
 */
public class UserTest
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
        myUser.setFirstName("Bryce");
        myUser.setLastName("Anderson");
    }



    /**
     * Test method for {@link model.User#getUserName()}.
     */
    @Test
    public void testGetUserByUserName_ba2012()
    {
        assertEquals(USERNAME, myUser.getUserName());
    }

    
   /**
     * Test method for {@link model.User#getOptions()}.
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
	 *  Test method for {@link model.User#getFirstName()}.
	 */
	    
	     
    @Test
    public void testGetFirstName()
    {
        assertEquals("Bryce", myUser.getFirstName());
    }

    /**
     Test method for {@link model.User#getLastName()}.
    */
    @Test
    public void testGetLastName()
    {
    	 assertEquals("Anderson", myUser.getLastName());
    }

    /**
     * Test method for {@link model.User#getKey()}.
     */
    @Test
    public void testGetKey()
    {
        assertEquals(USERNAME, myUser.getKey());
    }

    /**
      Test method for {@link model.User#toString()}.
    */
    @Test
    public void testToString()
    {
        assertEquals("User Name: " + USERNAME +" User Type: ParkManager", myUser.toString());
    }
    

    /**
     * Test method for {@link model.User#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject()
    {
    	assertEquals(myUser, myUser);
       
    }
    
    @Test
    public void testEqualsObject_notEqual() {
    	
    	assertFalse(myUser.equals(new ParkManager("Larry")));
    }

}
