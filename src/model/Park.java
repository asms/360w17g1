/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Park model class.
 * @author Steven Smith
 * @version 1.0
 */
public class Park implements UniqueObject {

	/**
	 * Generated serial version ID.
	 */
	private static final long serialVersionUID = -3504459221248501513L;
	
	/**
	 * The format for string output of a park.
	 */
	private static final String STRING_FORMAT = "%s, %s";
	
	/**
	 * The name of the park.
	 */
	private final String myName;
	
	/**
	 * The location of the park.
	 */
	private final String myLocation;
	
	private final Set<Job> myAssociatedJobs;
	
	/**
	 * Create a new instance of a park from a non-null name and non-null location.
	 */
	public Park(final String theName, final String theLocation) {
		myName = Objects.requireNonNull(theName);
		myLocation = Objects.requireNonNull(theLocation);
		myAssociatedJobs = new HashSet<Job>();
	}
	
	/**
	 * Associates the park with the current job.
	 * @param theJob the job
	 */
	public void associateWithJob(final Job theJob) {
		myAssociatedJobs.add(theJob);
	}
	
	/**
	 * Gets the list of associated jobs.
	 * @param theJob the job
	 */
	public Set<Job> getAssociatedJobs() {
		return myAssociatedJobs;
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
