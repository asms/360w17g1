package ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import users.User;

public class LoginView extends AbstractView {
	
	public LoginView(Scanner theScanner) {
		super(theScanner, User.GUEST.getInstance());
	}

	@Override
	public void show() {
		displayHeader();
		final String username = getString("Username");
	}


}
