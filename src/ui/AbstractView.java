/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.text.ParseException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

import model.JobController;
import model.JobDateTime;
import model.ParkController;
import model.UserController;
import model.AbstractUser;

/**
 * Abstract view class.
 * @author Steven Smith
 * @version 1.0
 *
 */
public abstract class AbstractView<T extends AbstractUser> {

	/** Possible states of the view. */
	public static enum Status {
		/** The view will continue looping. */
		RUN,
		/** The view will stop looping. */
		EXIT
	}

	/** The height of the console screen buffer. */
	protected static final int SCREEN_BUFFER_HEIGHT = 60;

	/** The width of the console screen buffer. */
	protected static final int SCREEN_BUFFER_WIDTH = 160;

	/** The header for the Urban Parks program. */
	private static final String HEADER = "Urban Parks"
			+ System.lineSeparator()
			+ "Welcome %s, logged in as a %s.";

	/** The format for a single line in a numbered list. */
	private static final String NUMBERED_LIST_FORMAT = "[%d] %s";

	/** A string of "=" that spans the width of the screen buffer. */
	protected static final String LINE = new String(new char[SCREEN_BUFFER_WIDTH]).replace("\0", "-");

	/** The format for a prompt prefix. */
	private static final String PROMPT = "%s: ";

	/** The scanner object. */
	protected final Scanner myScanner;

	/** The user accessing the view. */
	protected final AbstractUser myUser;

	/** The job controller for interfacing with persistent job data. */
	protected final JobController myJobController;

	/** The user controller for interfacing with persistent user data. */
	protected final UserController myUserController;

	/** The park controller for interfacing with persistent park data. */
	protected final ParkController myParkController;

	/** The current status of the view. */
	protected Status myStatus;

	/**
	 * Instantiates the view.
	 * <p>Concrete views have access to the {@link AbstractView#myUser} object that they passed to the super constructor.</p>
	 * <p>Concrete views have access to the protected controllers:
	 * <ul>
	 *   <li>{@link AbstractView#myJobController}</li>
	 *   <li>{@link AbstractView#myParkController}</li>
	 *   <li>{@link AbstractView#myUserController}</li>
	 * </ul>
	 * </p>
	 * <p>
	 * 	Concrete views also have access to various convenience methods that display information to the console and
	 * 	prompt for user input.
	 * </p>
	 * @param theUser the user accessing the view
	 * @throws NullPointerException if the scanner or user are null
	 */
	public AbstractView(final Scanner theScanner, final T theUser) {
		myScanner = Objects.requireNonNull(theScanner);
		myUser = Objects.requireNonNull(theUser);
		myJobController = new JobController();
		myParkController = new ParkController();
		myUserController = new UserController();
		myStatus = Status.RUN;
	}

	/**
	 * Displays a custom view to the console.
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
	 * Displays a horizontal line.
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
	 * 
	 * @param thePrompt the prompt
	 * @return the input string
	 * @throws NullPointerException if the prompt is null
	 */
	protected String getString(final String thePrompt) {
		System.out.print(String.format(PROMPT, Objects.requireNonNull(thePrompt)));
		return myScanner.nextLine();
	}

	/**
	 * Prompts the user for integer input.
	 * 
	 * @param thePrompt the prompt
	 * @param theMin the minimum value
	 * @param theMax the maximum value
	 * @return the input integer
	 * @throws IllegalArgumentException if the minimum value is less than the maximum value
	 * @throws NullPointerException if the prompt is null
	 */
	protected int getInteger(final String thePrompt, final int theMin, final int theMax) {
		if (theMin > theMax) {
			throw new IllegalArgumentException();
		}
		int integer = 0;
		boolean validated = false;

		while (!validated) {
			try {
				System.out.print(String.format(PROMPT, Objects.requireNonNull(thePrompt)));
				integer = Integer.parseInt(myScanner.nextLine());
				if (integer >= theMin && integer <= theMax) {
					validated = true;
				} else {
					printError(String.format("%d must be in the range [%d, %d]", integer, theMin, theMax));
				}
			} catch(NumberFormatException e) {
				printError("Not a valid integer.");
			}
		}
		return integer;
	}

	/**
	 * Prompt the user for boolean input.
	 * @param thePrompt the prompt
	 * @return the input boolean
	 * @throws NullPointerException if the prompt is null
	 */
	protected boolean getBooleanYesNo(final String thePrompt) {
		boolean validated = false;
		boolean yes = false;
		String input;
		while (!validated) {
			input = getString(Objects.requireNonNull(thePrompt));
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

	/**
	 * Pads the right side of a string with spaces.
	 * 
	 * @param theString the string to be padded.
	 * @param theWidth the maximum width of the padded string
	 * @return the padded string
	 * @throws NullPointerException if the string is null
	 * @throws IllegalArgumentException if the width is less than one
	 */
	protected String pad(final String theString, final int theWidth) {
		if (theWidth < 1) {
			throw new IllegalArgumentException();
		}
		StringBuilder sb = new StringBuilder(Objects.requireNonNull(theString));
		final int difference = theWidth - theString.length();
		if (difference > 0) {
			sb.append(new String(new char[difference]).replace("\0", " "));
		}
		return sb.toString();
	}

	/**
	 * Displays a numbered list of strings.
	 * 
	 * @param theList the list of objects
	 * @throws NullPointerException if the list is null
	 */
	protected void displayNumberedList(final String[] theList) {
		for(int i=0; i<Objects.requireNonNull(theList).length; i++) {
			print(String.format(NUMBERED_LIST_FORMAT, i+1, theList[i].toString()));
		}
	}

	/**
	 * Displays a title for a numbered list of objects and prompts the user to make a selection from that list. Offers
	 * alternative options.
	 * 
	 * @param theTitle the title of the list
	 * @param thePrompt the prompt
	 * @param theList the list
	 * @param theAlternativeOption an alternative option
	 * @return the object selected from the array
	 * @throws NullPointerException if any parameters are null
	 */
	protected <E> E getSelectionFromList(final String theTitle, final String thePrompt, final E[] theList,
			final Function<E, String> theToStringFunction, final String[] theAlternatives) {
		print(Objects.requireNonNull(theTitle));
		displayLine();
		final Stream<String> listAsStrings = Arrays.stream(Objects.requireNonNull(theList))
				.map(Objects.requireNonNull(theToStringFunction));
		displayNumberedList(Stream.concat(listAsStrings,
				Arrays.stream(Objects.requireNonNull(theAlternatives))).toArray(String[]::new));
		displayLine();
		final int index = getInteger(Objects.requireNonNull(thePrompt), 1, theList.length + 1);
		if (index == theList.length + 1) {
			return null;
		} else {
			return theList[index - 1];
		}
	}

	/**
	 * Prints a string to the console.
	 * 
	 * @param theString the string
	 * @throws NullPointerException if the string is null
	 */
	protected void print(String theString) {
		final String[] strings = Objects.requireNonNull(theString).split(System.lineSeparator());
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
	 * @throws NullPointerException if the prompt or command array is null
	 */
	protected Command getCommand(final String thePrompt, final Command[] theCommands) {
		displayNumberedList(Arrays.stream(Objects.requireNonNull(theCommands)).map(Object::toString).toArray(String[]::new));
		try {
			int commandNumber = getInteger(Objects.requireNonNull(thePrompt), 1, theCommands.length);
			if(commandNumber > 0 && commandNumber <= theCommands.length) {
				return theCommands[commandNumber - 1];
			} else {
				return null;
			}
		} catch(final InputMismatchException e) {
			print("Command not recognized.");
			return getCommand(thePrompt, theCommands);
		}

	}

	/**
	 * Prompts the user for a date.
	 * @param thePrompt the prompt, include date format (mm/dd/yyyy)
	 * @param theMin the lower bound on the date
	 * @param theMax the upper bound on the date
	 * @return the input date
	 * @throws NullPointerException if the prompt, minimum date, or maximum date are null
	 * @throws IllegalArgumentException if the minimum date is greater than the maximum date
	 */
	protected JobDateTime getDate(final String thePrompt, final JobDateTime theMin, final JobDateTime theMax) {
		if (Objects.requireNonNull(theMin).after(Objects.requireNonNull(theMax))) {
			throw new IllegalArgumentException();
		}
		JobDateTime date = new JobDateTime();
		boolean validated = false;
		while (!validated) {
			try {
				date = date.setFromDateString(getString(Objects.requireNonNull(thePrompt)));
				if (JobDateTime.intersects(date, date, theMin, theMax)) {
					validated = true;
				} else {
					printError("The date must be between " + theMin.toDateString() + " and " + theMax.toDateString() + ".");
				}
			} catch(final ParseException e) {
				printError("Date not formatted correctly. Format: mm/dd/yyyy.");
			}
		}
		return date;
	}

	/**
	 * Prompts the user for a time after a minimum time.
	 * @param thePrompt the prompt, include time format (hh:mm am/pm)
	 * @param theMin the lower bound on the time
	 * @return the input time as a date object
	 * @throws NullPointerException if the prompt or minimum date are null
	 */
	protected JobDateTime getTime(final String thePrompt, final JobDateTime theMin) {
		JobDateTime time = new JobDateTime();
		boolean validated = false;
		while (!validated) {
			try {
				time = time.setFromTimeString(getString(Objects.requireNonNull(thePrompt)));
				if (time.after(Objects.requireNonNull(theMin))) {
					validated = true;
				} else {
					printError("The time must be after " + theMin.toTimeString() + ".");
				}
			} catch(final ParseException e) {
				printError("Time not formatted correctly. Format: hh:mm am/pm.");
			}
		}
		return time;
	}

	/**
	 * Prompts the user for a time.
	 * @param thePrompt the prompt, include time format (HH:MM AM/PM)
	 * @return the input time as a date object
	 * @throws NullPointerException if the prompt is null
	 */
	protected JobDateTime getTime(final String thePrompt) {
		return getTime(Objects.requireNonNull(thePrompt), new JobDateTime().getStartOfDate());
	}

	/**
	 * Prints an error to the console.
	 * @param theError the error string
	 * @throws NullPointerException if the error string is null
	 */
	protected void printError(final String theError) {
		System.out.println(Objects.requireNonNull(theError));
	}

	/**
	 * Clears the console by outputting new lines equal to the height of the screen buffer.
	 */
	public void clear() {
		myScanner.reset();                            // Clears the scanner buffer.
		for(int i=0; i<SCREEN_BUFFER_HEIGHT; i++ ) {
			System.out.print(System.lineSeparator()); // Clears the screen.
		}
	}

	/**
	 * Puts the view into an exit state to break the loop and clears the screen.
	 */
	protected void exit() {
		clear();
		myStatus = Status.EXIT;
	}
}
