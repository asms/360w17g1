/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.util.Scanner;

import users.ParkManager;

/**
 * Entry point of the program.
 * @author Steven Smith
 * @version 0.1
 */
public final class Driver {
	
	private Driver() {}

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		//new LoginView(scanner).show();
		new ParkManagerView(scanner, new ParkManager("Steven")).show();
		scanner.close();
	}

}
