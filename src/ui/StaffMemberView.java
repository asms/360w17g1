/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Job;
import model.StaffMember;
import ui.Command.CommandExecutor;

/**
 * The view for staff members.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public class StaffMemberView extends AbstractView {

	private static enum COMMAND implements Command {
		VIEW_UPCOMMING_JOBS("View a calendar of upcoming jobs"),
		SET_MAX_PENDING_JOBS("Set maximum number of pending jobs"), LOGOUT("Logout");

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
	 * Creates a new staff member view.
	 * 
	 * @param theScanner
	 *            the scanner
	 * @param theUser
	 *            the user
	 */
	public StaffMemberView(final Scanner theScanner, final StaffMember theUser) {
		super(theScanner, theUser);
		commands = new HashMap<Command, CommandExecutor>();
		commands.put(COMMAND.VIEW_UPCOMMING_JOBS, new CommandExecutor() {
			@Override
			public void execute() {
				viewCalendarOfUpcomingJobs();
			}
		});
		commands.put(COMMAND.SET_MAX_PENDING_JOBS, new CommandExecutor() {
			@Override
			public void execute() {
				setMaxPendingJobs();
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
			COMMAND command = (COMMAND) getCommand("Enter a command", COMMAND.values());
			if (command != null) {
				commands.get(command).execute();
			}
		}
	}

	/**
	 * Method displays the upcoming jobs the user has.
	 */
	public void viewCalendarOfUpcomingJobs() {
		new CalendarWidget(myJobController.getUpcomingJobs(), myJobController.getMaximumNumberOfPendingJobs()).print();
		getString("Press enter to continue...");
	}
	
	/**
	 * Sets the maximum number of pending jobs.
	 */
	public void setMaxPendingJobs() {
		final String prompt = "Set Maximum number of jobs (current=" + myJobController.getMaximumNumberOfPendingJobs() + ")";
		final int max = getInteger(prompt, 0, Integer.MAX_VALUE);
		myJobController.setMaximumNumberOfPendingJobs(max);
	}

}
