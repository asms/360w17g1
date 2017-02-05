/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Park model class.
 * @author Steven Smith
 * @version 1.0
 */
public class Park implements Serializable, UniqueObject {

	/**
	 * Generated serial version ID.
	 */
	private static final long serialVersionUID = -3504459221248501513L;
	
	/**
	 * The format for string output of a park.
	 */
	private static final String STRING_FORMAT = "Park(name=\"%s\", location=\"%s\")";
	
	/**
	 * The name of the park.
	 */
	private final String myName;
	
	/**
	 * The location of the park.
	 */
	private final String myLocation;
	
	/**
	 * Create a new instance of a park from a non-null name and non-null location.
	 */
	public Park(final String theName, final String theLocation) {
		myName = Objects.requireNonNull(theName);
		myLocation = Objects.requireNonNull(theLocation);
	}
	
	/**
	 * Returns the name of the park.
	 * @return the name of the park
	 */
	public String getName() {
		return myName;
	}
	
	/**
	 * Returns the location of the park.
	 * @return the location of the park
	 */
	public String getLocation() {
		return myLocation;
	}
	
	@Override
	public String toString() {
		return String.format(STRING_FORMAT, myName, myLocation);
	}
	
	@Override
	public boolean equals(final Object theObject) {
		return (theObject instanceof Park)
				&& (myName.equals(((Park) theObject).myName))
				&& (myLocation.equals(((Park) theObject).myLocation));
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(myName, myLocation);
	}

	@Override
	public String getKey() {
		return myName;
	}
}
