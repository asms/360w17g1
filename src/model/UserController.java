
/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */

package model;

import java.util.ArrayList;



/**
 * User Controller
 * 
 * @author Bryce
 * @version 1
 *
 */
public class UserController extends AbstractController<User> {

	/**
	 * Adds a user 
	 * @param theUser  A user being added
	 */
	public void addUser(final User theUser) {
		add(theUser);
	}
	
	
	/**
	 * Returns a User with a specific user name.
	 * @param theName the name of a User
	 * @return the User that has the specified name
	 */
	public User getUserByName(final String theName) {
		return myList.get(theName);
	}
	
	/**
	 * Gets all the users
	 * @return all of the users in the system.
	 */
	public ArrayList<User> getAllUsers() {
		return new ArrayList<User>(myList.values());
	}

	/**
	 *  @return A list of all the Users 
	 */
	public ArrayList<User> getUserList() {

		return new ArrayList<User>(myList.values());
	}





}
