<<<<<<< Updated upstream
/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ParkManager extends AbstractUser {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -5509981249651008018L;

	/**
	 * The parks that the park manager manages.
	 */
	private final HashMap<String, Park> myParks;

	/**
	 * Instantiates the park manager. This should be created by the ParkController.
	 * @param theUserName the park manager's username
	 * @param theParks the parks that the park manager manages
	 */
	public ParkManager(final String theUserName) {
		super(theUserName);
		myParks = new HashMap<String, Park>();
	}

	/**
	 * Gets an unmodifiable set of the parks associated with the park manager.
	 * @return the set of parks
	 */
	public HashMap<String, Park> getAssociatedParks() {
		return myParks;
	}

	public void associate(final Park thePark) {
		myParks.put(thePark.getKey(), thePark);
	}

	public List<Job> getJobs() {
		return myParks.values().stream().flatMap(x -> x.getAssociatedJobs().stream()).collect(Collectors.toList());
	}

}
=======
package model;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ParkManager extends AbstractUser {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -5509981249651008018L;

	/**
	 * The parks that the park manager manages.
	 */
	private final HashMap<String, Park> myParks;

	/**
	 * Instantiates the park manager. This should be created by the ParkController.
	 * @param theUserName the park manager's username
	 * @param theParks the parks that the park manager manages
	 */
	public ParkManager(final String theUserName) {
		super(theUserName);
		myParks = new HashMap<String, Park>();
	}

	/**
	 * Gets an unmodifiable set of the parks associated with the park manager.
	 * @return the set of parks
	 */
	public HashMap<String, Park> getAssociatedParks() {
		return myParks;
	}

	public void associate(final Park thePark) {
		myParks.put(thePark.getKey(), thePark);
	}

	public List<Job> getJobs() {
		return myParks.values().stream().flatMap(x -> x.getAssociatedJobs().stream()).collect(Collectors.toList());
	}

}
>>>>>>> Stashed changes
