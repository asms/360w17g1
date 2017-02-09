package ui;

import java.util.Scanner;

import model.Volunteer;

/**
 * The view for volunteers.
 * @author Steven Smith
 * @version 1.0
 */
public class VolunteerView extends AbstractView {
	
	/**
	 * Creates a new volunteer view.
	 * @param theScanner the scanner object
	 * @param theUser the volunteer
	 */
	public VolunteerView(final Scanner theScanner, final Volunteer theUser) {
		super(theScanner, theUser);
	}

	@Override
	public void show() {
		displayHeader();
		myScanner.nextLine();
		//TODO: implement
	}

}
