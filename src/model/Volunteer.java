package model;

import java.util.Objects;

public class Volunteer extends AbstractUser {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -1831128233221318812L;
	
	private final String myFirstName;
	private final String myLastName;
	private final String myPhoneNumber;
	private final String myEmailAddress;
	
	/**
	 * Constructs a volunteer.
	 * @param theUserName the username
	 * @param theFirstName the user's first name
	 * @param theLastName the user's last name
	 * @param thePhoneNumber the user's phone number
	 * @param theEmailAddress the user's email address
	 */
	public Volunteer(final String theUserName, final String theFirstName, final String theLastName,
			final String thePhoneNumber, final String theEmailAddress) {
		super(theUserName);
		myFirstName = Objects.requireNonNull(theFirstName);
		myLastName = Objects.requireNonNull(theLastName);
		myPhoneNumber = Objects.requireNonNull(thePhoneNumber);
		myEmailAddress = Objects.requireNonNull(theEmailAddress);
	}
	
	public String getFirstName() {
		return myFirstName;
	}
	
	public String getLastName() {
		return myLastName;
	}
	
	public String getPhoneNumber() {
		return myPhoneNumber;
	}
	
	public String getEmailAddress() {
		return myEmailAddress;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s %s",
				myFirstName,
				myLastName,
				myPhoneNumber,
				myEmailAddress
		);
	}

}