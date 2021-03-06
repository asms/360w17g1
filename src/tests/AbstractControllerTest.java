/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;


import org.junit.Before;
import org.junit.Test;

import model.UserController;
import model.AbstractController;
import model.AbstractUser;
import model.JobController;
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
 * @author Amy I
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
	
	@Test
	public void testConstructor_FileNotExists_ExpectedCreatesFileAndHashMap() {
		final String fileName = "JobController_LIST_HashMap.ser";
		try {
			File file = new File(fileName);
			if (file.exists()) {
				file.delete();
			}
			JobController jc = new JobController();
			assertTrue(jc.getAllJobs().isEmpty());
			assertTrue(file.exists());
		} catch (Exception e) {
			fail(e.getMessage().toString());
		}
	}
	
	@Test
	public void testSerialization_AddUserToBeSerialized_ExpectedContainsUser() {
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
	
	@Test
	public void testSerializeToDisk_IOException_ReturnsFalse() {
		final String key = "/";
		final AbstractUser object = AbstractUser.GUEST;
		boolean expectedFalse = true;
		try {
			expectedFalse = AbstractController.serializeToDisk(key, object);
		} catch (Exception e) {
			fail(e.getMessage().toString());
		}
		assertFalse(expectedFalse);
	}

}
