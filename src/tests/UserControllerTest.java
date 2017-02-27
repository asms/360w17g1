package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.UserController;
import model.Volunteer;
import model.ParkManager;




/**
 * Tests for UserController class
 * @author Bryce
 * @version 1
 *
 */
public class UserControllerTest {

private UserController myUserController;
private ParkManager myPM;
private Volunteer myVol;

	@Before
	public void setUp() {
		myUserController = new UserController();
		myUserController.clear();
		myPM = new ParkManager("Name");
	}
	
	@After
	public void cleanup() {
		myUserController.clear();
	}


	@Test
	public void addUser_WithUser_True() {
		myUserController.addUser(myPM);
		assertEquals(myPM, myUserController.getUserByUserName("Name"));
		
	}
	
	
	@Test (expected = NullPointerException.class)
	public void addUser_NullUser() {
		
		myUserController.addUser(myVol);
	}
	
}



