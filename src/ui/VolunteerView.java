/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import exceptionsWithLongNames.AllreadySignedUpForJobOnThisDateException;
import model.JobController;
import model.Job;
import model.Job.WorkDuty;
import model.Park;
import model.Volunteer;
import ui.Command.CommandExecutor;

/**
 * The view for volunteers.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public class VolunteerView extends AbstractView<Volunteer> {

	private static enum COMMAND implements Command {
		SIGN_UP_FOR_JOBS("Sign up for a job"), LOGOUT("Logout");

		/**
		 * The human readable string for the command.
		 */
		private final String myValue;

		/**
		 * Assigns a human readable string to the command.
		 * 
		 * @param theValue
		 *            the string to be displayed for the command
		 */
		COMMAND(final String theValue) {
			myValue = theValue;
		}

		@Override
		public String toString() {
			return myValue;
		}
	}

	/**
	 * The list of all possible commands for a park manager.
	 */
	private final Map<Command, CommandExecutor> commands;

	/**
	 * Creates a new volunteer view.
	 * 
	 * @param theScanner
	 *            the scanner object
	 * @param theUser
	 *            the volunteer
	 */
	public VolunteerView(final Scanner theScanner, final Volunteer theUser) {
		super(theScanner, theUser);
		commands = new HashMap<Command, CommandExecutor>();
		commands.put(COMMAND.SIGN_UP_FOR_JOBS, new CommandExecutor() {
			@Override
			public void execute() {
				viewJobsForSigningUp();
			}
		});
		commands.put(COMMAND.LOGOUT, new CommandExecutor() {
			@Override
			public void execute() {
				exit();
			}
		});
	}

	@Override
	public void show() {
		while (myStatus == Status.RUN) {
			clear();
			displayHeader();
			displayLineBreak();
			// displayCurrentJobs();
			displayLineBreak();
			COMMAND command = (COMMAND) getCommand("Enter a command", COMMAND.values());
			if (command != null) {
				commands.get(command).execute();
			}
		}
	}

	/**
	 * Displays a view for finding and signing up for jobs.
	 */
	private void viewJobsForSigningUp() {
		displayLineBreak();
		int state = 1; // get park
		final Park[] parks = myParkController.getAllParks().toArray(new Park[0]);
		while (state == 1) {
			final Park park = getSelectionFromList("Parks", "Enter a park number", parks, p -> p.getName(),
					new String[] { "Back" });
			if (park != null) {
				state = 2; // get job
				while (state == 2) {
					displayLineBreak();
					final Job[] jobs = JobController
							.filterAtLeastMinimumDaysAhead(myJobController.getByPark(park).stream())
							.toArray(Job[]::new);
					final Job job = getSelectionFromList("Jobs", "Enter a job number to sign up", jobs,
							x -> x.getJobName(), new String[] { "Back" });
					if (job != null) {
						displayLineBreak();
						print(JobUIFormatter.format(job));
						final String command = getSelectionFromList("Commands", "Select a command number",
								new String[] { "Sign Up", "Back" }, x -> x, new String[0]);
						try {
							if (command.equals("Back")){
								
							} else if(myJobController.assertSigningUp((Volunteer) myUser, job)) {
								final WorkDuty duty = getSelectionFromList("Work Duty", "Select a skill level",
										WorkDuty.values(), x -> x.toString(), new String[] { "Cancel" });
								if (duty != null) {
									myJobController.signUp((Volunteer) myUser, job, duty);
								}
							} else {
								print("You cannot sign up for this job.");
							}
						} catch (final AllreadySignedUpForJobOnThisDateException e) {
							displayLineBreak();
							printError("YOU ARE ALREADY SIGNED UP FOR A JOB ON THIS DATE");
							getString("Press enter to continue");
						} 
						catch (final IllegalStateException theException) {
							
							printError(theException.getMessage()); // TODO:
							// Handle
							// different
							// illegal
							// states.
						}

					} else {
						state = 1;
					}
				}
			} else {
				state = 0;
			}
		}
	}

	// private void displayCurrentJobs() {
	// final List<Job> jobs = ((Volunteer) myUser).getPendingJobs();
	// print(String.format("%s%s%s%s%s",
	// pad("Pending Job", 25),
	// pad("Location", 50),
	// pad("Date", 15),
	// pad("Start time", 15),
	// pad("End time", 15)));
	// displayLine();
	// for (final Job job : jobs) {
	// print(String.format("%s%s%s%s%s",
	// pad(job.getJobName(), 25),
	// pad(job.getPark().getLocation(), 50),
	// pad(job.getDate().toDateString(), 15),
	// pad(job.getStartTime().toTimeString(), 15),
	// pad(job.getEndTime().toTimeString(), 15)));
	// }
	// if (jobs.size() == 0) {
	// print("You are not signed up for any pending jobs.");
	// }
	// displayLine();
	// }

}
