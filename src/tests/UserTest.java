/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import model.Park;
import model.ParkManager;
import model.User;

/**
 * Test Suite for User class.
 * 
 * @author Mohamed Mohamed
 * @version 1.0
 */
public class UserTest
{
	
	public String USERNAME = "user0001";
	public Park PARK_1 = new Park("Cherry Park", "1234 N 56th");

	public HashSet<Park> ASSOCIATED_PARK = new HashSet<Park>(Arrays.asList(PARK_1));
	
	
	
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
        myUser = new ParkManager(USERNAME, ASSOCIATED_PARK);
        myUser.setFirstName("Bryce");
        myUser.setLastName("Anderson");
    }



    /**
     * Test method for {@link model.User#getUserName()}.
     */
    @Test
    public void testGetUserName()
    {
        assertEquals(USERNAME, myUser.getUserName());
    }

   /**
     * Test method for {@link model.User#getOptions()}.
   */
    
//    @Test
//    public void testGetOptions()
//    {
//        fail("Not yet implemented");
//    }

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
        assertEquals("User Name: user0001 User Type: ParkManager", myUser.toString());
    }
    

    /**
     * Test method for {@link model.User#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject()
    {
       
    }

}
