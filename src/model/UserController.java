
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
	 *  @return A list of all the Users 
	 */
	public ArrayList<User> getUserList() {

		return new ArrayList<User>(myList.values());
	}

}
