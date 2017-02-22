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
public class LoginView extends AbstractView<AbstractUser> {
	
	/** The logged in user. */
	private AbstractUser myUser;

	/**
	 * Constructs the view for logging in.
	 * @param theScanner (non-null, open) scanner 
	 */
	public LoginView(Scanner theScanner) {
		super(theScanner, AbstractUser.GUEST);
	}
	
	/**
	 * Prompts for a username until a valid username has been entered.
	 */
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
	 * Returns the logged in user. You must call {@link LoginView#show()} first.
	 * 
	 * @return the logged in user
	 * @throws IllegalStateException if the user is null
	 */
	public AbstractUser getUser() {
		if (myUser == null) {
			throw new IllegalStateException();
		}
		return myUser;
	}
}
