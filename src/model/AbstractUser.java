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
	
	/** Guest user. */
	public static final Guest GUEST = new Guest();
	
	/** Private class for creating the constant {@link AbstractUser.GUEST}. */
	private static final class Guest extends AbstractUser {
		/** Generated serial version UID. */
		private static final long serialVersionUID = -6271192298505440768L;
		private static final String USERNAME = "Guest";
		public Guest() { super(USERNAME); }
	}

	private final String myUsername;

	/**
	 * Constructor for an Abstract User
	 * @param theUserName
	 */
	public AbstractUser(final String theUserName) {
		myUsername = Objects.requireNonNull(theUserName);
	}
	
	/**
	 * Gets the user name.
	 * @return user name
	 */
	public String getUserName() {
		return myUsername;
	}
	
	/**
	 * Gets the key.
	 * @return User name
	 */
	@Override
	public String getKey() {
		return myUsername;
	}

	
	/**
	 * Checks if two objects are equal
	 * @return true if they are equal, false otherwise
	 */
	@Override
	public boolean equals(final Object theObject) {
		
		return (theObject instanceof AbstractUser) 
				&& getKey().equals(((AbstractUser) theObject).getKey());
	}

	/**
	 * Hashes the user name. 
	 * @return the key for the hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getKey());
	}
}
