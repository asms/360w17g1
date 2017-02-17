/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.util.Objects;

/**
 * User Class
 * @author Bryce
 * @version 1
 */
public abstract class AbstractUser implements UniqueObject {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 4039931473200511934L;

	private final String myUsername;

	public AbstractUser(final String theUserName) {
		myUsername = Objects.requireNonNull(theUserName);
	}
	
	public String getUserName() {
		return myUsername;
	}
	
	@Override
	public String getKey() {
		return myUsername;
	}

	
	@Override
	public boolean equals(final Object theObject) {
		
		return (theObject instanceof AbstractUser) 
				&& getKey().equals(((AbstractUser) theObject).getKey());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getKey());
	}
}
