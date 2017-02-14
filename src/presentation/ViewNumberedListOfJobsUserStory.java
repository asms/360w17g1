/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package presentation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.JobController;
import controller.ParkController;
import controller.UserController;
import model.Job;
import model.Job.WorkDuty;
import model.Park;
import model.ParkManager;
import model.Volunteer;
import ui.Driver;

/**
 * Entry point of the program.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public final class ViewNumberedListOfJobsUserStory {

	public static void main(final String... args) {
		/* START SETUP */
		final UserController uc = new UserController();
		final ParkController pc = new ParkController();
		final JobController jc = new JobController();
		
		final Park sunset = new Park("Sunset", "1124 Sunset Drive W, University Place, WA 98466");
		final Park chambers = new Park("Chambers", "1111 Chambers Drive, University Place, WA 98466");
		
		final ParkManager manager = new ParkManager("robertl");
		manager.associateWithPark(sunset);
		manager.associateWithPark(chambers);
		
		final Volunteer volunteer = new Volunteer("carlyo", "Carly", "O'Hara", "253-425-2375", "carlyo@yahoo.com");
		
		final int FOUR_DAYS = 86400 * 4 * 1000;
		
		DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		
		try {
			final Job litter = new Job(manager, "Cleaning up litter", sunset, new Date(new Date().getTime() + FOUR_DAYS), timeFormat.parse("10:00 am"),
					timeFormat.parse("2:00 pm"), "Cleaning up after yesterday's little league tournament.", 2, 2,
					2);
			litter.addVolunteer(volunteer, WorkDuty.LIGHT);
			jc.addJob(litter);
		} catch (ParseException e) { System.out.println("FAIL"); }
		
		uc.addUser(manager);
		uc.addUser(volunteer);
		pc.addPark(sunset);
		pc.addPark(chambers);
		/* END SETUP */
		
		Driver.main(null);
		Driver.clearAll();
	}
}
