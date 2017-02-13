package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.UserController;
import model.ParkManager;
import model.AbstractUser;



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
	public void testGetUserByUserName_InputUserName_GetSameUserName() {
		final AbstractUser expectedManagerUser = new ParkManager("Ba2012");

		myUserController.addUser(expectedManagerUser);
//		System.out.println(expectedManagerUser.toString());
		
		final AbstractUser same = myUserController.getUserByUserName(expectedManagerUser.getKey());
//		System.out.println(same.toString());
	    assertEquals(expectedManagerUser, same);
	    
	}
	
	
	/**
	 * Test method for {@link UserController#getUserByName(final String theName)}
	 * Checks if a user is retrievable by user name after many users have been added.
	 */
	@Test
	public void testGetUserByUserNameFromMany_InputUserName_GetSameUserName() {
		for (int i = 0; i < 50; i++) {
			myUserController.addUser(new ParkManager("Ba2012 " + i));
			
		}
		
		final AbstractUser expectedManagerUser = new ParkManager("Ba2012 10");
		final AbstractUser same = myUserController.getUserByUserName(expectedManagerUser.getKey());
//		System.out.println(expectedManagerUser.toString() +"\n"+ same.toString());
		assertEquals(expectedManagerUser, same);
		
		
	}
	
	
	/**
	 * Test method for {@link UserController#getAllUsers()}
	 * Checks if all of the users are retrieved. 
	 */
	@Test
	public void testGetAllUsers_CallGetAllUsers_GetAllUsers() {
		
		final ArrayList<AbstractUser> users = new ArrayList<AbstractUser>();
		for (int i = 0; i < 100; i++) {
			users.add(new ParkManager("Ba2012 " + i));
			myUserController.addUser(new ParkManager("Ba2012 " + i));
		}
		final ArrayList<AbstractUser> allUsers = myUserController.getAllUsers();
		assertTrue(allUsers.containsAll(users) && users.containsAll(allUsers));
		
	}
	
	
}



