package ui;

import java.util.Scanner;

import model.User;

public class VolunteerView extends AbstractView {

	public VolunteerView(Scanner theScanner, User theUser) {
		super(theScanner, theUser);
	}

	@Override
	public void show() {
		displayHeader();
		myScanner.nextLine();
	}

}
