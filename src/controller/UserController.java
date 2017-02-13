/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */

package controller;

import java.util.ArrayList;

import model.AbstractUser;



/**
 * User Controller
 * 
 * @author Bryce
 * @version 1
 *
 */
public class UserController extends AbstractController<AbstractUser> {

	/**
	 * Adds a user 
	 * @param theUser  A user being added
	 */
	public void addUser(final AbstractUser theUser) {
		add(theUser);
	}
	
	
	/**
	 * Returns a User with a specific user name.
	 * @param theName the name of a User
	 * @return the User that has the specified name
	 */
	public AbstractUser getUserByUserName(final String theName) {
		return myList.get(theName);
	}
	
	/**
	 * Gets all the users
	 * @return all of the users in the system.
	 */
	public ArrayList<AbstractUser> getAllUsers() {
		return new ArrayList<AbstractUser>(myList.values());
	}

	/**
	 *  @return A list of all the Users 
	 */
	public ArrayList<AbstractUser> getUserList() {

		return new ArrayList<AbstractUser>(myList.values());
	}





}
