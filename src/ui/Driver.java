/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.util.Scanner;

import model.Park;
import model.ParkController;
import users.ParkManager;

/**
 * Entry point of the program.
 * 
 * @author Steven Smith
 * @version 0.1
 */
public final class Driver {

	private static ParkManagerView pmv;

	private Driver() {
	}

	public static void main(String[] args) {

		ParkController pm = new ParkController();
		if (pm.getAllParks().isEmpty()) {
			pm.addPark(new Park("Test Park", "Location of test park"));
		}

		final Scanner scanner = new Scanner(System.in);
		// new LoginView(scanner).show();
		new ParkManagerView(scanner, new ParkManager("Steven")).show();
		scanner.close();
	}

}
