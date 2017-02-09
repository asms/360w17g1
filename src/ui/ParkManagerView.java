/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Job;
import model.JobController;
import model.Park;
import ui.Command.CommandExecutor;
import users.User;

/**
 * View for park managers only
 * .
 * @author Steven Smith
 * @version 1.0
 */
public class ParkManagerView extends AbstractView {
	
	/**
	 * BR: A job cannot be scheduled more than one month in the future.
	 */
	private static final int MAX_FUTURE_DATE = 1;
	
	/**
	 * Possible commands a park manager can execute.
	 */
	private static enum COMMAND implements Command {
		CREATE_NEW_JOB("Create a new job"),
		VIEW_JOBS("View jobs"),
		VIEW_VOLUNTEERS("View volunteers"),
		LOGOUT("Logout");
		
		/**
		 * The human readable string for the command.
		 */
		private final String myValue;
		
		/**
		 * Assigns a human readable string to the command.
		 * @param theValue the string to be displayed for the command
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
	 * Instantiates the park manager view.
	 * @param theScanner the scanner object
	 * @param theUser the logged in user
	 */
	public ParkManagerView(final Scanner theScanner, final User theUser) {
		super(theScanner, theUser);
		commands = new HashMap<Command, CommandExecutor>();
		commands.put(COMMAND.CREATE_NEW_JOB, new CommandExecutor() {
			@Override
			public void execute() {
				createNewJob();
			}
		});
		commands.put(COMMAND.VIEW_JOBS, new CommandExecutor() {
			@Override
			public void execute() {
				viewJobs();
			}
		});
		commands.put(COMMAND.VIEW_VOLUNTEERS, new CommandExecutor() {
			@Override
			public void execute() {
				viewVolunteers();
			}
		});
		commands.put(COMMAND.LOGOUT, new CommandExecutor() {
			@Override
			public void execute() {
				logout();
			}
		});
	}
	
	@Override
	public void show() {
		while (myStatus == Status.RUN) {
			clear();
			displayHeader();
			COMMAND command = (COMMAND) getCommand("Enter a command", COMMAND.values());
			if (command != null) {
				commands.get(command).execute();
			}
		}
	}
	
	/**
	 * User Story: As a Park Manager I want to submit a new job.
	 */
	private void createNewJob() {
		displayTitle("Create a new job");
		final String name = getString("Enter job title");
		final Park park = getSelectionFromList("Parks",
				"Enter park number",
				myParkController.getAllParks().toArray(new Park[]{}));
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, MAX_FUTURE_DATE);
		Date date;
		boolean validDate = false;
		do {
			date = getDate("Enter date(MM/DD/YYYY)", new Date(System.currentTimeMillis()), calendar.getTime());
			if (myJobController.canAddWithDate(date)) {
				validDate = true;
			} else {
				printError("Maximum jobs per day (" + JobController.MAX_JOBS_PER_DAY + ") already reached.");
			}
		} while(!validDate);
		final Date startTime = getTime("Enter start time(HH:MM AM/PM)");
		final Date endTime = getTime("Enter end time(HH:MM AMP/PM)");
		final String description = getString("Enter description");
		final int numLightVolunteers = getInteger("Enter number of light-duty volunteers");
		final int numMediumVolunteers = getInteger("Enter number of medium-duty volunteers");
		final int numHeavyVolunteers = getInteger("Enter number of heavy-duty volunteers");
		
		final Job job = new Job(
					name,
					park,
					date,
					startTime,
					endTime,
					description,
					numLightVolunteers,
					numMediumVolunteers,
					numHeavyVolunteers
				);
		displayTitle(job.toString());
		final boolean shouldSubmit = getBooleanYesNo("Are you sure you want to submit this job (Y/N)");
		
		if (shouldSubmit) {
			myJobController.addJob(job);
			//TODO: message
		} else {
			//TODO: message
		}
		getString("Press enter to continue...");
	}
	
	/**
	 * Views all jobs. Not a user story, but useful for debugging job creation.
	 */
	private void viewJobs() {
		displayTitle("All Jobs");
		displayNumberedList(myJobController.getUpcomingJobs().toArray());
		getString("Press enter to continue...");
	}
	
	/**
	 * User Story: As a Park Manager I want to view a numbered list of Volunteers for a job (past or present) in the
	 * parks that I manage.
	 */
	private void viewVolunteers() {
		/*
		 * TODO:
		 * 
		 * ALTERNATIVELY, this can be a subcommand of the view jobs method.
		 * 
		 * Get all past and present jobs.
		 * Get volunteers associated with those jobs.
		 * Display volunteers in a numbered list.
		 */
	}
	
	/**
	 * Exits the view.
	 */
	private void logout() {
		exit();
	}
}
