/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

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
	 * Instantiates the park manager. This should be created by the ParkController.
	 * @param theUserName the park manager's username
	 */
	public ParkManager(final String theUserName) {
		super(theUserName);
	}


}
