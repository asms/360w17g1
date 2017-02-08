/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Job;
import model.Park;
import ui.Command.CommandExecutor;
import users.User;

public class ParkManagerView extends AbstractView {

	private static enum COMMAND implements Command {
		CREATE_NEW_JOB("Create a new job"), LOGOUT("Logout");

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
		final Park park = getSelectionFromList("Parks", "Enter park number", new Park[] {
				new Park("Name 1", "Location 1"), new Park("Name 2", "Location 2"), new Park("Name 3", "Location 3") });
		final Date date = getDate("Enter date(MM/DD/YYYY)");
		final Date startTime = getTime("Enter start time(HH:MM AM/PM)");
		final Date endTime = getTime("Enter end time(HH:MM AMP/PM)");
		final String description = getString("Enter description");
		final int numLightVolunteers = getInteger("Enter number of light-duty volunteers");
		final int numMediumVolunteers = getInteger("Enter number of medium-duty volunteers");
		final int numHeavyVolunteers = getInteger("Enter number of heavy-duty volunteers");

		final Job job = new Job(name, park, date, startTime, endTime, description, numLightVolunteers,
				numMediumVolunteers, numHeavyVolunteers);
		displayTitle(job.toString());
		final boolean shouldSubmit = getBooleanYesNo("Are you sure you want to submit this job (Y/N)");

		if (shouldSubmit) {
			myJobController.addJob(job);
		} else {
			// TODO: message
		}
	}

	private void logout() {
		exit();
	}
}
