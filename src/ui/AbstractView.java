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

import controller.JobController;
import controller.ParkController;
import controller.UserController;
import model.AbstractUser;

/**
 * Abstract view class.
 * @author Steven Smith
 * @version 1.0
 *
 */
public abstract class AbstractView {
	
	/**
	 * Possible states of the view.
	 */
	public static enum Status {
		RUN,
		EXIT
	}
	
	/**
	 * The height of the screen buffer.
	 */
	private static final int SCREEN_BUFFER_HEIGHT = 60;
	
	/**
	 * The width of the screen buffer.
	 */
	private static final int SCREEN_BUFFER_WIDTH = 120;
	
	/**
	 * The header for the Urban Parks program.
	 */
	private static final String HEADER = "Urban Parks"
			+ System.lineSeparator()
			+ "Welcome %s, logged in as a %s.";
	
	/**
	 * The format for a single line in a numbered list.
	 */
	private static final String NUMBERED_LIST_FORMAT = "[%d] %s";
	
	/**
	 * A string of "=" that spans the width of the screen buffer.
	 */
	protected static final String LINE = new String(new char[SCREEN_BUFFER_WIDTH]).replace("\0", "-");
	
	/**
	 * The format for a prompt prefix.
	 */
	private static final String PROMPT = "%s: ";
	
	/**
	 * The scanner object.
	 */
	protected final Scanner myScanner;
	
	/**
	 * The user accessing the view.
	 */
	protected final AbstractUser myUser;
	
	/**
	 * The job controller for interfacing with persistent job data.
	 */
	protected final JobController myJobController;
	
	/**
	 * The user controller for interfacing with persistent user data.
	 */
	protected final UserController myUserController;
	
	/**
	 * The park controller for interfacing with persistent park data.
	 */
	protected final ParkController myParkController;
	
	/**
	 * The current status of the view.
	 */
	protected Status myStatus = Status.RUN;
	
	/**
	 * Instantiates the view with the controllers to be accessed by concrete views.
	 * @param theUser the user accessing the view
	 */
	public AbstractView(final Scanner theScanner, final AbstractUser theUser) {
		myScanner = theScanner;
		myUser = theUser;
		myJobController = new JobController();
		myParkController = new ParkController();
		myUserController = new UserController();
	}
	
	/**
	 * Displays the view to the console.
	 */
	public abstract void show();
	
	
	/**
	 * Displays the header.
	 */
	protected void displayHeader() {
		if (myUser != null) {
			System.out.println(String.format(HEADER, myUser != null ? myUser.getUserName() : "guest", myUser.getClass().getSimpleName()));
		} else {
			System.out.println("Urban Parks");
		}
	}
	
	/**
	 * Displays a line.
	 */
	protected void displayLine() {
		System.out.println(LINE);
	}
	
	/**
	 * Displays a line break.
	 */
	protected void displayLineBreak() {
		System.out.print(System.lineSeparator());
	}
	
	/**
	 * Prompts the user for string input.
	 * @param thePrompt the prompt
	 * @return the input string
	 */
	protected String getString(final String thePrompt) {
		if (thePrompt != null) {
			System.out.print(String.format(PROMPT, thePrompt));
		}
		return myScanner.nextLine();
	}
	
	/**
	 * Prompts the user for integer input.
	 * @param thePrompt the prompt
	 * @param theMin the minimum value
	 * @param theMax the maximum value
	 * @return the input integer
	 */
	protected int getInteger(final String thePrompt, final int theMin, final int theMax) {
		int integer = 0;
		boolean validated = false;
		
		while (!validated) {
			try {
				System.out.print(String.format(PROMPT, thePrompt));
				integer = Integer.parseInt(myScanner.nextLine());
				if (integer >= theMin && integer <= theMax) {
					validated = true;
				} else {
					printError("Out of range."); //TODO: be more descriptive
				}
			} catch(NumberFormatException e) {
				printError("Invalid number."); //TODO: be more descriptive
			}
		}
		return integer;
	}
	
	/**
	 * Prompt the user for boolean input.
	 * @param thePrompt the prompt
	 * @return the input boolean
	 */
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
	
	protected String pad(final String theString, final int theWidth) {
		String out = theString;
		final int difference = theWidth - theString.length();
		if (difference > 0) {
			out += new String(new char[difference]).replace("\0", " ");
		}
		return out;
	}
	
	/**
	 * Displays a numbered list of objects, calling their toString methods.
	 * @param theList the list of objects
	 */
	protected void displayNumberedList(final Object[] theList) {
		for(int i=0; i<theList.length; i++) {
			print(String.format(NUMBERED_LIST_FORMAT, i+1, theList[i].toString()));
		}
	}
	
	/**
	 * Displays a title for a numbered list and prompts the user to make a selection from that list.
	 * @param theTitle the title of the list
	 * @param thePrompt the prompt
	 * @param theList the list
	 * @return the object selected from the array
	 */
	protected <T> T getSelectionFromList(final String theTitle, final String thePrompt, final T[] theList) {
		print(theTitle);
		displayLine();
		displayNumberedList(theList);
		displayLine();
		final int index = getInteger(thePrompt, 1, theList.length);
		return theList[index - 1];
	}
	
	/**
	 * Prints a string to the console.
	 * @param theString the string
	 */
	protected void print(String theString) {
		final String[] strings = theString.split(System.lineSeparator());
		for (String string : strings) {
			if (string.length() > SCREEN_BUFFER_WIDTH) {
				string = string.substring(0, SCREEN_BUFFER_WIDTH - 3) + "...";
			}
			System.out.println(string);
		}
		
	}

	/**
	 * Prompts a user to select a command from a list of commands.
	 * @param thePrompt the prompt
	 * @param theCommands the list of commands
	 * @return the selected command
	 */
	protected Command getCommand(final String thePrompt, final Command[] theCommands) {
		displayNumberedList(theCommands);
		try {
			int commandNumber = getInteger(thePrompt, 1, theCommands.length);
			if(commandNumber > 0 && commandNumber <= theCommands.length) {
				return theCommands[commandNumber - 1];
			} else {
				return null;
			}
		} catch(final InputMismatchException e) {
			//TODO: output error
			return getCommand(thePrompt, theCommands);
		}
		
	}
	
	/**
	 * Prompts the user for a date.
	 * @param thePrompt the prompt, include date format (MM/DD/YYYY)
	 * @param theMin the lower bound on the date
	 * @param theMax the upper bound on the date
	 * @return the input date
	 */
	protected Date getDate(final String thePrompt, final Date theMin, final Date theMax) {
		final DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;
		boolean validated = false;
		while (!validated) {
			try {
				date = format.parse(getString(thePrompt));
				if (date.after(theMin) && date.before(theMax)) {
					validated = true;
				} else {
					printError("Out of range."); //TODO: be more descriptive
				}
			} catch(final ParseException e) {
				printError("Invalid date."); //TODO: be more descriptive
			}
		}
		return date;
	}
	
	/**
	 * Prompts the user for a time
	 * @param thePrompt the prompt, include time format (HH:MM AM/PM)
	 * @param theMin the lower bound on the time
	 * @return the input time as a date object
	 */
	protected Date getTime(final String thePrompt, final Date theMin) {
		final DateFormat format = new SimpleDateFormat("hh:mm a");
		Date time = null;
		boolean validated = false;
		while (!validated) {
			try {
				time = format.parse(getString(thePrompt));
				if (time.after(theMin)) {
					validated = true;
				} else {
					printError("Out of range."); //TODO: be more descriptive
				}
			} catch(final ParseException e) {
				printError("Invaid time."); //TODO: be more descriptive
			}
		}
		return time;
	}
	
	protected Date getTime(final String thePrompt) { //TODO: reduce code redundancy
		final DateFormat format = new SimpleDateFormat("hh:mm a");
		Date time = null;
		boolean validated = false;
		while (!validated) {
			try {
				time = format.parse(getString(thePrompt));
				validated = true;
			} catch(final ParseException e) {
				printError("Invaid time."); //TODO: be more descriptive
			}
		}
		return time;
	}
	
	/**
	 * Prints an error to the console.
	 * @param theError the error.
	 */
	protected void printError(final String theError) {
		System.out.println(theError);
	}
	
	/**
	 * Clears the console by outputting new lines equal to the height of the screen buffer.
	 */
	public void clear() {
		myScanner.reset(); // Clears the scanner buffer.
		for(int i=0; i<SCREEN_BUFFER_HEIGHT; i++ ) {
			System.out.print(System.lineSeparator()); // Clears the screen.
		}
	}
	
	/**
	 * Puts the view into an exit state to break the loop.
	 */
	protected void exit() {
		clear();
		myStatus = Status.EXIT;
	}
}
