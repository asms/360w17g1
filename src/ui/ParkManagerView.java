/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ui.Command.CommandExecutor;
import users.User;

public class ParkManagerView extends AbstractView {
	
	private static enum COMMAND implements Command {
		CREATE_NEW_JOB("Create a new job"),
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
		
	}
	
	private void logout() {
		exit();
	}
}
