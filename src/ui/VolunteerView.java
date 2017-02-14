/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import controller.JobController;
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
public class VolunteerView extends AbstractView {

	private static enum COMMAND implements Command {
		VIEW_JOBS("View jobs"), LOGOUT("Logout");

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
		commands.put(COMMAND.VIEW_JOBS, new CommandExecutor() {
			@Override
			public void execute() {
				viewJobs();
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
			displayCurrentJobs();
			displayLineBreak();
			COMMAND command = (COMMAND) getCommand("Enter a command", COMMAND.values());
			if (command != null) {
				commands.get(command).execute();
			}
		}
	}
	//BR: A volunteer cannot sign up for more than one job on any given day.
	//BR: A volunteer may sign up only if the job is at least 3 calendar days from the current date.

	private void viewJobs() {
		displayLineBreak();
		int state = 1; // get park
		final Park[] parks = myParkController.getAllParks().toArray(new Park[0]);
		while(state == 1) {
			final Park park = getSelectionFromList("Parks", "Enter a park number", parks, "Back");
			if (park != null) {
				state = 2; // get job
				while (state == 2) {
					displayLineBreak();
					final String[] jobNames = JobController.filterAtLeastThreeDaysAheadandNoSameDayConflict
					                (myJobController.getByPark(park).stream()).map(x -> x.getJobName()).collect(Collectors.toList()).toArray(new String[0]);
					final String jobName = getSelectionFromList("Jobs", "Enter a job number to sign up", jobNames, "Back");
					final Job job = myJobController.getJob(jobName + park.toString());
					if (job != null) {
						displayLineBreak();
						displayJob(job);
						final String command = getSelectionFromList("Commands", "Select a command number", new String[] {"Sign Up", "Back"});
						if (command.equals("Sign Up") && myJobController.canSignUp((Volunteer) myUser, job.getDate())) {
							
							final String duty = getSelectionFromList("Work Duty", "Select a skill level",
									new String[]{WorkDuty.LIGHT.toString(), WorkDuty.MEDIUM.toString(), WorkDuty.HEAVY.toString()}, "Cancel");
							if (duty != null) {
								if (job.addVolunteer((Volunteer) myUser, WorkDuty.valueOf(duty))) {
									((Volunteer) myUser).addJob(job);
									myUserController.addUser(myUser);
									myJobController.addJob(job);
									//TODO: Confirmation
									getString("Press enter to continue...");
								} else {
									print("No more workers of that type are needed.");
								}
							}
						} else if(!command.equals("Back")) {
							print("You cannot sign up for this job.");
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
	
	private void displayJob(final Job theJob) {
		
	}
	
	private void displayCurrentJobs() {
		final List<Job> jobs = ((Volunteer) myUser).getPendingJobs();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		print(String.format("%s%s%s%s",
				pad("Pending Job", 30),
				pad("Date", 15),
				pad("Start time", 15),
				pad("End time", 15)));
		displayLine();
		for (final Job job : jobs) {
			print(String.format("%s%s%s%s", 
					pad(job.getJobName(), 30),
					pad(dateFormat.format(job.getDate()), 15),
					pad(timeFormat.format(job.getStartTime()), 15),
					pad(timeFormat.format(job.getEndTime()), 15)));
		}
		if (jobs.size() == 0) {
			print("You are not signed up for any pending jobs.");
		}
		displayLine();
	}

}
