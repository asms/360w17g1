package ui;

import java.util.Scanner;

import model.User;

/**
 * The view for logging in as a user.
 * @author Steven Smith
 * @version 1.0
 */
public class LoginView extends AbstractView {
	
	/**
	 * The logged in user.
	 */
	private User myUser;

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
			myUser = myUserController.getUserByName(getString("Username"));
			if (myUser == null) {
				printError("Access denied.");
			}
		}
	}
	
	/**
	 * Returns the logged in user.
	 * @return the user
	 */
	public User getUser() {
		return myUser;
	}

}
