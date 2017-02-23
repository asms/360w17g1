/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package presentation;

import java.text.ParseException;
import model.JobController;
import model.JobDate;
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
		
		final Volunteer volunteer = new Volunteer("carlyo", "Carly", "O'Hara", "253-425-2375", "carlyo@yahoo.com");
		
		final Volunteer crazyjoe = new Volunteer("crazyjoe", "Joe", "Joe", "666-666-6666", "crazyjoe@loco.com");
		crazyjoe.setBlackballedFlag(true);
		
		try {
			final Job litter = new Job(manager, "Cleaning up litter", sunset,
					new JobDate().addDays(4), new JobDate().setFromTimeString("10:00 am"),
					new JobDate().setFromTimeString("2:00 pm"), "Cleaning up after yesterday's little league tournament.", 2, 2,
					2);
			final Job painting = new Job(manager, "Painting Parking Lines", sunset,
					new JobDate().addDays(4), new JobDate().setFromTimeString("2:00 pm"),
					new JobDate().setFromTimeString("5:00 pm"), "Repainting the faded lines in the parking lot.", 2, 2,
					2);
			final Job collectFrogs = new Job(manager, "Collect Frogs", sunset,
					new JobDate().addDays(2), new JobDate().setFromTimeString("2:00 pm"),
					new JobDate().setFromTimeString("5:00 pm"), "Humanely collecting nuisance frogs and relocating them.", 2, 2,
					2);
			sunset.associateWithJob(litter);
			sunset.associateWithJob(painting);
			sunset.associateWithJob(collectFrogs);
			jc.addJob(litter);
			jc.addJob(painting);
			jc.addJob(collectFrogs);
		} catch (ParseException e) { System.out.println("FAIL"); }
		
		uc.addUser(manager);
		uc.addUser(volunteer);
		uc.addUser(crazyjoe);
		pc.addPark(sunset);
		/* END SETUP */
		
		Driver.main(null);
	}
}
