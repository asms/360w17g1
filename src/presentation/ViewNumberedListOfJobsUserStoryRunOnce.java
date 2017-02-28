/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package presentation;

import java.text.ParseException;
import model.JobController;
import model.JobDateTime;
import model.ParkController;
import model.UserController;
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
public final class ViewNumberedListOfJobsUserStoryRunOnce {

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
		
		
		try {
			final Job litter = new Job(manager, "Cleaning up litter", sunset, new JobDateTime().addDays(4), new JobDateTime().addDays(5), new JobDateTime().setFromTimeString("10:00 am"),
					new JobDateTime().setFromTimeString("2:00 pm"), "Cleaning up after yesterday's little league tournament.", 2, 2,
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
