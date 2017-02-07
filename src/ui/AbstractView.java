/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.JobController;
import model.ParkController;
import model.UserController;
import users.User;

public abstract class AbstractView {
	
	public static enum Status {
		RUN,
		EXIT
	}
	
	private static final int SCREEN_BUFFER_HEIGHT = 60;
	private static final int SCREEN_BUFFER_WIDTH = 30;
	private static final String HEADER = "Urban Parks"
			+ System.lineSeparator()
			+ "Welcome, %s (%s).";
	private static final String NUMBERED_LIST_FORMAT = "[%d] %s";
	private static final String LINE = new String(new char[SCREEN_BUFFER_WIDTH]).replace("\0", "=");
	private static final String TITLE = LINE + System.lineSeparator() + "%s" + System.lineSeparator() + LINE;
	private static final String PROMPT = "%s: ";
	
	protected final Scanner myScanner;
	protected final User myUser;
	protected final JobController myJobController;
	protected final UserController myUserController;
	protected final ParkController myParkController;
	
	protected Status myStatus = Status.RUN;
	
	public AbstractView(final Scanner theScanner, final User theUser) {
		myScanner = theScanner;
		myUser = theUser;
		myJobController = new JobController();
		myParkController = new ParkController();
		myUserController = new UserController();
	}

	public abstract void show();
	
	protected void displayHeader() {
		System.out.println(String.format(HEADER, myUser.getName(), myUser.getClass().getSimpleName()));
	}
	
	protected void displayTitle(final String theTitle) {
		System.out.println(String.format(TITLE, theTitle));
	}
	
	protected String getString(final String thePrompt) {
		System.out.print(String.format(PROMPT, thePrompt));
		return myScanner.nextLine();
	}
	
	protected int getInteger(final String thePrompt) {
		System.out.print(String.format(PROMPT, thePrompt));
		return Integer.parseInt(myScanner.nextLine()); 
	}
	
	protected boolean getBooleanYesNo(final String thePrompt) {
		boolean validated = false;
		boolean yes = false;
		String input;
		while (!validated) {
			input = getString(thePrompt);
			if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
				yes = true;
				validated = true;
			} else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
				yes = false;
				validated = true;
			}
		}
		return yes;
	}
	
	protected void displayNumberedList(final Object[] theList) {
		for(int i=0; i<theList.length; i++) {
			System.out.println(String.format(NUMBERED_LIST_FORMAT, i, theList[i].toString()));
		}
	}
	
	protected <T> T getSelectionFromList(final String theTitle, final String thePrompt, final T[] theList) {
		displayTitle(theTitle);
		displayNumberedList(theList);
		final int index = getInteger(thePrompt);
		return theList[index];
	}
	
	protected Command getCommand(final String thePrompt, final Command[] theCommands) {
		displayNumberedList(theCommands);
		try {
			int commandNumber = getInteger(thePrompt);
			if(commandNumber >= 0 && commandNumber < theCommands.length) {
				return theCommands[commandNumber];
			} else {
				return null;
			}
		} catch(final InputMismatchException e) {
			return getCommand(thePrompt, theCommands);
		}
		
	}
	
	protected Date getDate(final String thePrompt) {
		final DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;
		boolean validated = false;
		while (!validated) {
			try {
				date = format.parse(getString(thePrompt));
				validated = true;
			} catch(final ParseException e) {
				System.out.println("Invaid date.");
			}
		}
		return date;
	}

	protected Date getTime(final String thePrompt) {
		final DateFormat format = new SimpleDateFormat("hh:mm a");
		Date time = null;
		boolean validated = false;
		while (!validated) {
			try {
				time = format.parse(getString(thePrompt));
				validated = true;
			} catch(final ParseException e) {
				System.out.println("Invaid time.");
			}
		}
		return time;
	}
	
	public void clear() {
		myScanner.reset(); // Clears the scanner buffer.
		for(int i=0; i<SCREEN_BUFFER_HEIGHT; i++ ) {
			System.out.print(System.lineSeparator()); // Clears the screen.
		}
	}
	
	protected void exit() {
		myStatus = Status.EXIT;
	}
}
