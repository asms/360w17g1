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
import model.Park;
import model.ParkManager;
import model.StaffMember;
import model.Volunteer;
import ui.Driver;

/**
 * Entry point of the program.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public final class VolunteerForJobAndViewListingsUserStory {

	public static void main(final String... args) {
		/* START SETUP */
		final UserController uc = new UserController();
		final ParkController pc = new ParkController();
		final JobController jc = new JobController();
		
		final Park sunset = new Park("Sunset", "1124 Sunset Drive W, University Place, WA 98466");
		
		final StaffMember staff = new StaffMember("jim");
		
		final ParkManager manager = new ParkManager("robertl");
		manager.associateWithPark(sunset);
		
		final Volunteer volunteer = new Volunteer("carlyo", "Carly", "O'Hara", "253-425-2375", "carlyo@yahoo.com");
		
		final Volunteer crazyjoe = new Volunteer("crazyjoe", "Joe", "Joe", "666-666-6666", "crazyjoe@loco.com");
		crazyjoe.setBlackballedFlag(true);
		
		final int FOUR_DAYS = 86400 * 4 * 1000;
		
		DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		
		try {
			final Job litter = new Job(manager, "Cleaning up litter", sunset,
					new Date(new Date().getTime() + FOUR_DAYS), timeFormat.parse("10:00 am"),
					timeFormat.parse("2:00 pm"), "Cleaning up after yesterday's little league tournament.", 2, 2,
					2);
			final Job painting = new Job(manager, "Painting Parking Lines", sunset,
					new Date(new Date().getTime() + FOUR_DAYS), timeFormat.parse("2:00 pm"),
					timeFormat.parse("5:00 pm"), "Repainting the faded lines in the parking lot.", 2, 2,
					2);
			final Job collectFrogs = new Job(manager, "Collect Frogs", sunset,
					new Date(new Date().getTime() + FOUR_DAYS * 2), timeFormat.parse("2:00 pm"),
					timeFormat.parse("5:00 pm"), "Humanely collecting nuisance frogs and relocating them.", 2, 2,
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
		uc.addUser(staff);
		pc.addPark(sunset);
		/* END SETUP */
		
		Driver.main(null);
		Driver.clearAll();
	}
}
