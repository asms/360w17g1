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
import model.AbstractUser;
import model.Volunteer;

/**
 * Entry point of the program.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public final class Driver {

	private Driver() {
	}

	public static void main(String[] args) {
		/* START SETUP */
		ParkController pc = new ParkController();
		if (pc.getAllParks().isEmpty()) {
			pc.addPark(new Park("Cherry Parkes", "1234 N 56th Street, Tacoma, WA 98409"));
		}

		UserController uc = new UserController();
		ParkManager dev = new ParkManager("dev");
		uc.addUser(dev);
		dev.associate(pc.getParkByName("Cherry Parkes"));

		uc.addUser(new StaffMember("amy"));
		uc.addUser(new Volunteer("eli"));
		/* END SETUP */

		final Scanner scanner = new Scanner(System.in);
		while (true) {
			LoginView loginView = new LoginView(scanner);
			loginView.show();
			final AbstractUser user = loginView.getUser();
			if (user instanceof Volunteer) {
				new VolunteerView(scanner, (Volunteer) user).show();
			} else if (user instanceof ParkManager) {
				new ParkManagerView(scanner, (ParkManager) user).show();
			} else if (user instanceof StaffMember) {
				new StaffMemberView(scanner, (StaffMember) user).show();
			} else {
				break;
			}
		}
		scanner.close();
	}

}
