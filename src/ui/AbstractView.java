/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.util.Scanner;

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
	
	protected Status myStatus = Status.RUN;
	
	public AbstractView(final Scanner theScanner, final User theUser) {
		myScanner = theScanner;
		myUser = theUser;
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
		return myScanner.nextInt();
	}
	
	protected void displayNumberedList(final Object[] theList) {
		for(int i=0; i<theList.length; i++) {
			System.out.println(String.format(NUMBERED_LIST_FORMAT, i, theList[i].toString()));
		}
	}
	
	protected Command getCommand(final String thePrompt, final Command[] theCommands) {
		displayNumberedList(theCommands);
		System.out.print(String.format(PROMPT, thePrompt));
		int commandNumber = myScanner.nextInt();
		if(commandNumber >= 0 && commandNumber < theCommands.length) {
			return theCommands[commandNumber];
		} else {
			return null;
		}
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
