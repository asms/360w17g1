/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import model.UserController;
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
	public void testSerialization_AddUser_ContainsUser() {
		// Creates a random Volunteer and adds it to my controller.
		// myController then should serialize the data.
		Volunteer testObject = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
		myController.addUser(testObject);

		// A new instance of UserController should deserialize the data and the
		// list should contain the user we added above.
		myController = new UserController();
		ArrayList<AbstractUser> objects = myController.getAllUsers();
		assertTrue(objects.contains(testObject));
	}
	

	@Test
	public void testClear_ClearList_UserListShouldBeEmpty(){
		myController.addUser(new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com"));
		myController.clear();
		assertEquals(myController.getAllUsers(), new UserController().getAllUsers());
	}

}
