/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import controller.UserController;
import model.AbstractUser;
import model.Volunteer;

/**
 * Tests the AbstractController class.
 * 
 * <p>
 * The primary use for the abstract controller class is to control serialization
 * and deserialization.
 * </p>
 * 
 * @author Steven Smith
 * @version 1.0
 *
 */
public class AbstractControllerTest {

	/**
	 * JobController objects used for testing.
	 */
	private UserController myController;

	@Before
	public void setUp() {
		myController = new UserController();
	}

	/*
	 * Currently this does not pass because AbstractController is not complete.
	 */
	@Test
	public void testSerialization() {
		// Creates a random Volunteer and adds it to my controller.
		// myController then should serialize the data.
		Volunteer testObject = new Volunteer("username");
		myController.addUser(testObject);

		// A new instance of UserController should deserialize the data and the
		// list should contain the user we added above.
		myController = new UserController();
		ArrayList<AbstractUser> objects = myController.getAllUsers();
		for (AbstractUser user : objects) {
//			System.out.println(user.getUserName());
		}
		assertTrue(objects.contains(testObject));
	}
	

	@Test
	public void testClear_UserListShouldBeEmpty(){
		myController.addUser(new Volunteer("USER"));
		myController.clear();
		assertEquals(myController.getAllUsers(), new UserController().getAllUsers());
	}

}
