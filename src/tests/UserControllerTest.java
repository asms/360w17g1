package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

import model.User;
import model.UserController;
import model.Park;
import model.ParkManager;



/**
 * Tests for UserController class
 * @author Bryce
 * @version 1
 *
 */
public class UserControllerTest {

private UserController myUserController;

	@Before
	public void setUp() throws Exception {
		myUserController = new UserController();
	}

	/**
	 * Test method for {@link UserController#getUserByName(final String theName)}
	 * Checks is a user is retrievable by user name.
	 */
	@Test
	public void testGetUserByUserName() {
		final HashSet<Park> associatedParks = new HashSet<Park>();
		associatedParks.add(new Park("Cherry Park", "1234 N 56th St"));
		final User expectedManagerUser = new ParkManager("Ba2012", associatedParks);

		myUserController.addUser(expectedManagerUser);
		System.out.println(expectedManagerUser.toString());
		
		final User same = myUserController.getUserByName(expectedManagerUser.getKey());
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
			myUserController.addUser(new ParkManager("Ba2012 " + i, new HashSet<Park>()));
			
		}
		
		final User expectedManagerUser = new ParkManager("Ba2012 10", new HashSet<Park>());
		final User same = myUserController.getUserByName(expectedManagerUser.getKey());
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
			users.add(new ParkManager("Ba2012 " + i, new HashSet<Park>()));
			myUserController.addUser(new ParkManager("Ba2012 " + i, new HashSet<Park>()));
		}
		final ArrayList<User> allUsers = myUserController.getAllUsers();
		assertTrue(allUsers.containsAll(users) && users.containsAll(allUsers));
		
	}
	
	
}



