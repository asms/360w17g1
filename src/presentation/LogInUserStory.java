/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package presentation;

import controller.UserController;
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
public final class LogInUserStory {

	public static void main(final String... args) {
		/* START SETUP */
		final UserController uc = new UserController();
		final Volunteer volunteer = new Volunteer("kellyp", "Kelly", "Peterson", "253-123-4567", "kellypeterson5@gmail.com");
		final StaffMember staff = new StaffMember("arthurl");
		final ParkManager manager = new ParkManager("shawnj");
		uc.addUser(volunteer);
		uc.addUser(staff);
		uc.addUser(manager);
		/* END SETUP */
		
		Driver.main(null);
		Driver.clearAll();
	}
}
