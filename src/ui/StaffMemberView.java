package ui;

import java.util.Scanner;

import model.StaffMember;

/**
 * The view for staff members.
 * @author Steven Smith
 * @version 1.0
 */
public class StaffMemberView extends AbstractView {

	/**
	 * Creates a new staff member view.
	 * @param theScanner the scanner
	 * @param theUser the user
	 */
	public StaffMemberView(final Scanner theScanner, final StaffMember theUser) {
		super(theScanner, theUser);
	}

	@Override
	public void show() {
		displayHeader();
		myScanner.nextLine();
		//TODO: implement
	}

}
