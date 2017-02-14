/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import controller.JobController;
import model.Job;
import model.Park;
import model.ParkManager;
import model.Volunteer;
import ui.Command.CommandExecutor;

/**
 * View for park managers only .
 * 
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
		CREATE_NEW_JOB("Create a new job"), VIEW_VOLUNTEERS("View volunteers"), LOGOUT(
				"Logout");

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
	 * Instantiates the park manager view.
	 * 
	 * @param theScanner
	 *            the scanner object
	 * @param theUser
	 *            the logged in user
	 */
	public ParkManagerView(final Scanner theScanner, final ParkManager theUser) {
		super(theScanner, theUser);
		commands = new HashMap<Command, CommandExecutor>();
		commands.put(COMMAND.CREATE_NEW_JOB, new CommandExecutor() {
			@Override
			public void execute() {
				createNewJob();
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
	 * User Story: As a Park Manager I want to submit a new job.
	 */
	private void createNewJob() {
		print("Create a new job");
		displayLineBreak();
		if (myJobController.getUpcomingJobs().size() < myJobController.getMaximumNumberOfPendingJobs()) {
			final Park park = getSelectionFromList("Parks", "Enter park number",
					myParkController.getAllParks().toArray(new Park[] {}));
			
			String name;
			boolean validName = false;
			do {
				name = getString("Enter job title");
				if (myJobController.canAddWithNameAtPark(name, park)) {
					validName = true;
				} else {
					printError("A job by that name already exists for this park.");
				}
			} while (!validName);
			
			final Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, MAX_FUTURE_DATE);
			Date date;
			boolean validDate = false;
			do {
				print("Job date must be no more 30 days after today.");
				date = getDate("Enter date(MM/DD/YYYY)", new Date(System.currentTimeMillis()), calendar.getTime());
				if (myJobController.canAddWithDate(date)) {
					validDate = true;
				} else {
					printError("Maximum jobs per day (" + JobController.MAX_JOBS_PER_DAY + ") already reached.");
				}
			} while (!validDate);
			final Date startTime = getTime("Enter start time(HH:MM AM/PM)");
			final Date endTime = getTime("Enter end time(HH:MM AM/PM)", startTime);
			final String description = getString("Enter description");
			final int numLightVolunteers = getInteger("Enter number of light-duty volunteers",
											0, Job.MAX_VOLUNTEERS);
			final int numMediumVolunteers = getInteger("Enter number of medium-duty volunteers",
											0, Job.MAX_VOLUNTEERS - numLightVolunteers);
			final int numHeavyVolunteers = getInteger("Enter number of heavy-duty volunteers",
											0, Job.MAX_VOLUNTEERS - numLightVolunteers - numMediumVolunteers);
	
			final Job job = new Job((ParkManager) myUser, name, park, date, startTime, endTime, description, numLightVolunteers,
					numMediumVolunteers, numHeavyVolunteers);
			displayLine();
			print(job.toString());
			displayLine();
			final boolean shouldSubmit = getBooleanYesNo("Are you sure you want to submit this job (Y/N)");
	
			if (shouldSubmit) {
		        park.associateWithJob(job);
		        myParkController.addPark(park);
				myJobController.addJob(job);
				print("Job added.");
			} else {
				print("Job not added.");
			}
		} else {
			printError("The maximum number of pending jobs has already been reached.");
		}
		getString("Press enter to continue...");
	}

	/**
	 * User Story: As a Park Manager I want to view a numbered list of
	 * Volunteers for a job (past or present) in the parks that I manage.
	 */
	private void viewVolunteers() {
		displayLineBreak();
		final Park[] parks = ((ParkManager) myUser).getAssociatedParks().toArray(new Park[0]);
		final Park park = getSelectionFromList("Parks", "Enter a park number", parks);
		displayLineBreak();
		final String[] jobNames = myJobController.getByPark(park).stream().map(x -> x.getJobName()).collect(Collectors.toList()).toArray(new String[0]);
		final String jobName = getSelectionFromList("Jobs", "Enter a job number", jobNames);
		final Job job = myJobController.getJob(jobName + park.toString());
		displayLineBreak();
		final Set<Volunteer> volunteers = job.getVolunteers();
		final int numberVolunteers = volunteers.size();
		if (numberVolunteers > 0) {
			print("Volunteers (" + numberVolunteers + "/30):");
			displayVolunteerGrid(volunteers);
		} else {
			print("There are no volunteers for this job.");
		}
		getString("Press enter to continue...");
	}
	
	protected void displayVolunteerGrid(final Set<Volunteer> theVolunteers) {
		StringBuilder sb = new StringBuilder();
		Iterator<Volunteer> it = theVolunteers.iterator();
		sb.append(pad("No.", 20));
		sb.append(pad("First Name", 20));
		sb.append(pad("Last Name", 20));
		sb.append(pad("Phone Number", 20));
		sb.append(pad("Email", 20));
		sb.append(System.lineSeparator());
		sb.append(LINE);
		sb.append(System.lineSeparator());
		for (int i=0; i<theVolunteers.size(); i++) {
			final Volunteer volunteer = it.next();
			sb.append(pad("[" + (i+1)+ "]", 20));
			String[] args = volunteer.toString().split(" ");
			for (int j = 0; j<args.length; j++) {
				sb.append(pad(args[j], 20));
			}
			sb.append(System.lineSeparator());
		}
		print(sb.toString());
	}

}
