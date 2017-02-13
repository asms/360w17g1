/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.util.Date;
import java.util.Scanner;

import controller.JobController;
import controller.ParkController;
import controller.UserController;
import model.Park;
import model.ParkManager;
import model.StaffMember;
import model.AbstractUser;
import model.Job;
import model.Volunteer;

/**
 * Entry point of the program.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public final class Driver {

	private static ParkController pc;
	private static UserController uc;
	private static JobController jc;

	private Driver() {
	}

	public static void main(String[] args) {
		/* START SETUP */
		pc = new ParkController();
		uc = new UserController();
		jc = new JobController();

		if (pc.getAllParks().isEmpty()) {
			pc.addPark(new Park("Cherry Parkes", "1234 N 56th Street, Tacoma, WA 98409"));
		}

		// Park manager
		ParkManager dev = new ParkManager("dev");
		uc.addUser(dev);
		dev.associate(pc.getParkByName("Cherry Parkes"));

		// Staff member
		StaffMember sm = new StaffMember("amy");
		Date time = new Date();
		sm.addPastJobs(
				new Job("theName", new Park("thePark", "theLocation"), time, time, time, "theDescription", 1, 2, 3));
		uc.addUser(sm);

		// Volunteer
		uc.addUser(new Volunteer("eli"));

		// Adding to Job Manager
		jc.addJob(new Job("theName", new Park("thePark", "theLocation"), time, time, time, "theDescription", 1, 2, 3));

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
