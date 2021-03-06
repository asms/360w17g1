/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 
 * @author Steven
 *
 */
public class Volunteer extends AbstractUser {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -1831128233221318812L;
	
	private final String myFirstName;
	private final String myLastName;
	private final String myPhoneNumber;
	private final String myEmailAddress;
	public Set<Job> myJobs;
	
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
		myJobs = new HashSet<Job>();
	}
	
	
	/**
	 * Gets the Volunteers first name.
	 * @return first name
	 */
	public String getFirstName() {
		return myFirstName;
	}
	
	/**
	 * Gets the Volunteers last name.
	 * @return last name
	 */
	public String getLastName() {
		return myLastName;
	}
	
	/**
	 * Gets the phone number.
	 * @return phone number
	 */
	public String getPhoneNumber() {
		return myPhoneNumber;
	}
	
	/**
	 * Gets the email address
	 * @return email address
	 */
	public String getEmailAddress() {
		return myEmailAddress;
	}
	
	/**
	 * Adds the Job to the Volunteer's jobs.
	 * If the job is null it will not be added.
	 * @param A valid job
	 * @throws NullPointerException
	 */
	public void addJob(final Job job) {
		myJobs.add(Objects.requireNonNull(job));
		
	}
	
}