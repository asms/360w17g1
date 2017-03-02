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
public final class VolunteerForJobAndViewListingsUserStoryRunOnce {

	public static void main(final String... args) {
		/* START SETUP */
		final UserController uc = new UserController();
		final ParkController pc = new ParkController();
		final JobController jc = new JobController();
		
		final Park sunset = new Park("Sunset", "1124 Sunset Drive W, University Place, WA 98466");
		

		
		final ParkManager manager = new ParkManager("robertl");
		manager.associateWithPark(sunset);
		pc.addPark(sunset);
		final Volunteer volunteer = new Volunteer("carlyo", "Carly", "O'Hara", "253-425-2375", "carlyo@yahoo.com");
		
		final Volunteer crazyjoe = new Volunteer("crazyjoe", "Joe", "Joe", "666-666-6666", "crazyjoe@loco.com");

		
		try {
			final Job litter = new Job(manager, "Cleaning up litter", sunset,
					new JobDateTime().addDays(0), new JobDateTime().addDays(5), new JobDateTime().setFromTimeString("10:00 AM"),
					new JobDateTime().setFromTimeString("02:00 PM"), "Cleaning up after yesterday's little league tournament.", 2, 2,
					2);
			final Job painting = new Job(manager, "Painting Parking Lines", sunset,
					new JobDateTime().addDays(4), new JobDateTime().addDays(5), new JobDateTime().setFromTimeString("02:00 PM"),
					new JobDateTime().setFromTimeString("05:00 PM"), "Repainting the faded lines in the parking lot.", 2, 2,
					2);
			final Job collectFrogs = new Job(manager, "Collect Frogs", sunset,
					new JobDateTime().addDays(2), new JobDateTime().addDays(3), new JobDateTime().setFromTimeString("02:00 PM"),
					new JobDateTime().setFromTimeString("05:00 PM"), "Humanely collecting nuisance frogs and relocating them.", 2, 2,
					2);
			jc.addJob(litter);
			jc.addJob(painting);
			jc.addJob(collectFrogs);
		} catch (ParseException e) { System.out.println("FAIL"); }
		
		uc.addUser(manager);
		uc.addUser(volunteer);
		uc.addUser(crazyjoe);
		
		/* END SETUP */
		

	}
}
