/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package presentation;

import controller.UserController;

import ui.Driver;

/**
 * Entry point of the program.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public final class ChangeMaxPendingJobsUserStory {

	public static void main(final String... args) {
		/* START SETUP */
		final UserController uc = new UserController();

		/* END SETUP */
		
		Driver.main(null);
		Driver.clearAll();
	}
}
