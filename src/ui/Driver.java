/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import controller.JobController;
import controller.ParkController;
import controller.UserController;
import model.Park;
import model.ParkManager;
import model.StaffMember;
import model.AbstractUser;
import model.Job;
import model.Job.WorkDuty;
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
		final ParkController pc = new ParkController();
		final UserController uc = new UserController();
		final JobController jc = new JobController();
		final Volunteer eli = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
		final StaffMember amy = new StaffMember("amy");
		final ParkManager dev = new ParkManager("dev");
		final Park cherryParkes = new Park("Cherry Parkes", "1234 N 56th Street, Tacoma, WA 98409");
		final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	final DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		Job cherryParkesJob;
		try {
			cherryParkesJob = new Job(dev, "Annual Park Cleanup",
					cherryParkes,
					dateFormat.parse("2/14/2017"),
					timeFormat.parse("10:00 am"),
					timeFormat.parse("2:00 pm"),
					"Cleaning up after the annual festival.",
					1, 2, 3);
			cherryParkesJob.addVolunteer(eli, WorkDuty.LIGHT);
			cherryParkes.associateWithJob(cherryParkesJob);
			jc.addJob(cherryParkesJob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dev.associateWithPark(cherryParkes);
		pc.addPark(cherryParkes);
		uc.addUser(dev);
		uc.addUser(amy);
		uc.addUser(eli);
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
