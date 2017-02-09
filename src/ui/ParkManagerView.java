/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Job;
import model.JobController;
import model.Park;
import ui.Command.CommandExecutor;
import users.User;

public class ParkManagerView extends AbstractView {
	
	/**
	 * BR: A job cannot be scheduled more than one month in the future.
	 */
	private static final int MAX_FUTURE_DATE = 1;
	
	private static enum COMMAND implements Command {
		CREATE_NEW_JOB("Create a new job"),
		VIEW_JOBS("View jobs"),
		LOGOUT("Logout");
		
		private final String myValue;
		
		COMMAND(final String theValue) {
			myValue = theValue;
		}
		
		@Override
		public String toString() {
			return myValue;
		}
	}
	
	private final Map<Command, CommandExecutor> commands;
	
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
	
	private void createNewJob() {
		displayTitle("Create a new job");
		final String name = getString("Enter job title");
		final Park park = getSelectionFromList("Parks",
				"Enter park number",
				new Park[] {new Park("Name 1", "Location 1"), new Park("Name 2", "Location 2"), new Park("Name 3", "Location 3")});
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, MAX_FUTURE_DATE);
		final Date date = getDate("Enter date(MM/DD/YYYY)", new Date(System.currentTimeMillis()), calendar.getTime());
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
	
	private void viewJobs() {
		displayTitle("All Jobs");
		displayNumberedList(myJobController.getUpcomingJobs().toArray());
		getString("Press enter to continue...");
	}
	
	private void logout() {
		exit();
	}
}
