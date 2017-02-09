package ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginView extends AbstractView {
	
	public LoginView(Scanner theScanner) {
		super(theScanner, null);
	}

	@Override
	public void show() {
		displayHeader();
		final String username = getString("Username");
	}


}
