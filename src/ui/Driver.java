/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.util.Scanner;

import controller.ParkController;
import controller.UserController;
import model.Park;
import model.ParkManager;
import model.StaffMember;
import model.User;
import model.Volunteer;

/**
 * Entry point of the program.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public final class Driver {

	private static ParkManagerView pmv;

	private Driver() {
	}

	public static void main(String[] args) {
		ParkController pc = new ParkController();
		if (pc.getAllParks().isEmpty()) {
			pc.addPark(new Park("Test Park", "Location of test park"));
		}

		UserController uc = new UserController();
		if (uc.getAllUsers().isEmpty()) {
			uc.addUser(new ParkManager("dev")); // Creates a park manager called
												// dev.
		}

		final Scanner scanner = new Scanner(System.in);
		LoginView loginView = new LoginView(scanner);
		loginView.show();
		final User user = loginView.getUser();
		if (user instanceof Volunteer) {
			new VolunteerView(scanner, (Volunteer) user).show();
		} else if (user instanceof ParkManager) {
			new ParkManagerView(scanner, (ParkManager) user).show();
		} else if (user instanceof StaffMember) {
			new StaffMemberView(scanner, (StaffMember) user).show();
		}
		scanner.close();
	}

}
