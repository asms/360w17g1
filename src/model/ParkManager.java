/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;
import java.util.HashSet;
import java.util.Set;

/**
 * Park manager class.
 * @author Steven Smith
 * @version 1.0
 */
public class ParkManager extends AbstractUser {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -5509981249651008018L;

	/**
	 * The parks that the park manager manages.
	 */
	private final Set<Park> myParks;

	/**
	 * Instantiates the park manager. This should be created by the ParkController.
	 * @param theUserName the park manager's username
	 * @param theParks the parks that the park manager manages
	 */
	public ParkManager(final String theUserName) {
		super(theUserName);
		myParks = new HashSet<Park>();
	}
	
	/**
	 * Associates the park with the park manager.
	 * @param thePark the park
	 */
	public void associateWithPark(final Park thePark) {
		myParks.add(thePark);
	}

	/**
	 * Gets an unmodifiable set of the parks associated with the park manager.
	 * @return the set of parks
	 */
	public Set<Park> getAssociatedParks() {
		return myParks;
	}

}
