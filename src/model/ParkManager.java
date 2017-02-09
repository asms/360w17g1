package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ParkManager extends User {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -5509981249651008018L;
	
	/**
	 * The parks that the park manager manages.
	 */
	private final ArrayList<Park> myParks;

	/**
	 * Instantiates the park manager. This should be created by the ParkController.
	 * @param theUserName the park manager's username
	 * @param theParks the parks that the park manager manages
	 */
	public ParkManager(final String theUserName) {
		super(theUserName);
		myParks = new ArrayList<Park>();
	}
	
	/**
	 * Gets an unmodifiable set of the parks associated with the park manager.
	 * @return the set of parks
	 */
	public ArrayList<Park> getAssociatedParks() {
		return (ArrayList<Park>) myParks.clone();
	}
	
	public void associate(final Park thePark) {
		myParks.add(thePark);
	}

}
