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
import model.User;
import model.Volunteer;

/**
 * Tests the AbstractController class.
 * 
 * <p>
 * The primary use for the abstract controller class is to control serialization and deserialization.
 * </p>
 * @author Steven Smith
 * @version 1.0
 *
 */
public class AbstractControllerTest {
	
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
		Volunteer testObject = new Volunteer("username");
		myController.addUser(testObject);
		
		
		myController = new UserController();
		ArrayList<User> objects = myController.getAllUsers();
		for (User user : objects) {
			System.out.println(user.getUserName());
		}
		assertTrue(objects.contains(testObject));
	}

}
