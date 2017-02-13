/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.util.Scanner;

import model.AbstractUser;

/**
 * The view for logging in as a user.
 * @author Steven Smith
 * @version 1.0
 */
public class LoginView extends AbstractView {
	
	/**
	 * The logged in user.
	 */
	private AbstractUser myUser;

	/**
	 * Constructs the view for logging in.
	 * @param theScanner
	 */

	public LoginView(Scanner theScanner) {
		super(theScanner, null);
	}

	@Override
	public void show() {
		displayHeader();
		while (myUser == null) {
			myUser = myUserController.getUserByUserName(getString("Username"));
			if (myUser == null) {
				printError("Access denied.");
			}
		}
	}
	
	/**
	 * Returns the logged in user.
	 * @return the user
	 */
	public AbstractUser getUser() {
		return myUser;
	}


}
