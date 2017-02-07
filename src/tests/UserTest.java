/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.User;

/**
 * Test Suite for User class.
 * 
 * @author Mohamed Mohamed
 * @version 1.0
 */
public class UserTest
{
    /**
     * User object to user for testing.
     */
    User myUser;

    /**
     * Tests constructor and sets up user object.
     */
    @Before
    public void setUp()
    {
        myUser = new User("mohamedm21");
    }



    /**
     * Test method for {@link model.User#getUserName()}.
     */
    @Test
    public void testGetUserName()
    {
        assertEquals("mohamedm21", myUser.getUserName());
    }

    /*/**
     * Test method for {@link model.User#getOptions()}.
     
    @Test
    public void testGetOptions()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.User#login(java.lang.String)}.
     
    @Test
    public void testLogin()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.User#getFirstName()}.
     
    @Test
    public void testGetFirstName()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.User#getLastName()}.
     
    @Test
    public void testGetLastName()
    {
        fail("Not yet implemented");
    }*/

    /**
     * Test method for {@link model.User#getKey()}.
     */
    @Test
    public void testGetKey()
    {
        assertEquals("mohamedm21", myUser.getKey());
    }

    /*/**
     * Test method for {@link model.User#toString()}.
    
    @Test
    public void testToString()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.User#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject()
    {
        User u = new User("mohamedm21");
        assertEquals(true, u.equals(myUser) );
    }

}
