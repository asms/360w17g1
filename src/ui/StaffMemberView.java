package ui;

import java.util.Scanner;

import model.User;

public class StaffMemberView extends AbstractView {

	public StaffMemberView(Scanner theScanner, User theUser) {
		super(theScanner, theUser);
	}

	@Override
	public void show() {
		displayHeader();
	}

}
