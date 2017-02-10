package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.UserController;
import model.ParkManager;
import model.User;



/**
 * Tests for UserController class
 * @author Bryce
 * @version 1
 *
 */
public class UserControllerTest {

private UserController myUserController;

	@Before
	public void setUp() {
		myUserController = new UserController();
		myUserController.clear();
	}
	
	@After
	public void cleanup() {
		myUserController.clear();
	}

	/**
	 * Test method for {@link UserController#getUserByName(final String theName)}
	 * Checks is a user is retrievable by user name.
	 */
	@Test
	public void testGetUserByUserName() {
		final User expectedManagerUser = new ParkManager("Ba2012");

		myUserController.addUser(expectedManagerUser);
		System.out.println(expectedManagerUser.toString());
		
		final User same = myUserController.getUserByUserName(expectedManagerUser.getKey());
		System.out.println(same.toString());
	    assertEquals(expectedManagerUser, same);
	    
	}
	
	
	/**
	 * Test method for {@link UserController#getUserByName(final String theName)}
	 * Checks if a user is retrievable by user name after many users have been added.
	 */
	@Test
	public void testGetUserByUserNameFromMany() {
		for (int i = 0; i < 50; i++) {
			myUserController.addUser(new ParkManager("Ba2012 " + i));
			
		}
		
		final User expectedManagerUser = new ParkManager("Ba2012 10");
		final User same = myUserController.getUserByUserName(expectedManagerUser.getKey());
		System.out.println(expectedManagerUser.toString() +"\n"+ same.toString());
		assertEquals(expectedManagerUser, same);
		
		
	}
	
	
	/**
	 * Test method for {@link UserController#getAllUsers()}
	 * Checks if all of the users are retrieved. 
	 */
	@Test
	public void testGetAllUsers() {
		
		final ArrayList<User> users = new ArrayList<User>();
		for (int i = 0; i < 100; i++) {
			users.add(new ParkManager("Ba2012 " + i));
			myUserController.addUser(new ParkManager("Ba2012 " + i));
		}
		final ArrayList<User> allUsers = myUserController.getAllUsers();
		assertTrue(allUsers.containsAll(users) && users.containsAll(allUsers));
		
	}
	
	
}



