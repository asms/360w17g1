/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package presentation;

import model.UserController;
import model.ParkManager;
import model.Volunteer;
import ui.Driver;

/**
 * Entry point of the program.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public final class LogInUserStoryRunOnce {

	public static void main(final String... args) {
		/* START SETUP */
		final UserController uc = new UserController();
		final Volunteer volunteer = new Volunteer("kellyp", "Kelly", "Peterson", "253-123-4567", "kellypeterson5@gmail.com");
		final ParkManager manager = new ParkManager("shawnj");
		uc.addUser(volunteer);
		uc.addUser(manager);
		/* END SETUP */
	}
}
