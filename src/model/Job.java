/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import exceptions.ExceedsMaxVolunteersException;


/**
 * Job model class.
 * 
 * @author Mohamed Mohamed
 * @version 1.0
 */
public final class Job implements UniqueObject {
	/**
	 * Generated serial version id.
	 */
	private static final long serialVersionUID = -4115864921352899827L;

	public static final int MAX_VOLUNTEERS = 10;

	/**
	 * The park manager who created the job.
	 */
	private ParkManager myManager;

	/**
	 * The name of the job.
	 */
	private String myName;

	/**
	 * The park the job is associated with.
	 */
	private Park myPark;

	/**
	 * The start date of the job.
	 */
	private final JobDateTime myStartDate;
	
	/**
	 * The end date of the job.
	 */
	private final JobDateTime myEndDate;

	/**
	 * The start time of the job.
	 */
	private final JobDateTime myStartTime;

	/**
	 * The end time of the job.
	 */
	private final JobDateTime myEndTime;

	/**
	 * The description of the job.
	 */
	private String myDescription;

	/** Maps of volunteer's email, and work categories. */
	private Map<Volunteer, WorkDuty> volunteers;

	private Map<WorkDuty, Integer> neededVolunteers;

	/**
	 * Creates a job object.
	 * 
	 * @param theName
	 *            the name of the job.
	 * @param thePark
	 *            the name of the park this job is associated with.
	 * @param theLocation
	 *            the location of the job.
	 * @param theDate
	 *            the date of the job.
	 * @param theTime
	 *            the time of the job.
	 * @param theDescription
	 *            the description of the job.
	 * @param theDifficulty
	 *            the difficulty of the job.
	 * @throws ExceedsMaxVolunteersException if the number of volunteers exceeds the maximum number of volunteers
	 */
	public Job(final ParkManager theManager, final String theName, final Park thePark, final JobDateTime theStartDate,
			final JobDateTime theEndDate, final JobDateTime theStartTime, final JobDateTime theEndTime,
			final String theDescription, final int theLightDuty, final int theMediumDuty, final int theHeavyDuty) {
		myManager = Objects.requireNonNull(theManager);
		myName = Objects.requireNonNull(theName);

		myPark = Objects.requireNonNull(thePark);

		myStartDate = Objects.requireNonNull(theStartDate);
		myEndDate = Objects.requireNonNull(theEndDate);
		myStartTime = Objects.requireNonNull(theStartTime);
		myEndTime = Objects.requireNonNull(theEndTime);

		myDescription = Objects.requireNonNull(theDescription);

		neededVolunteers = new HashMap<WorkDuty, Integer>();
		
		if (theLightDuty + theMediumDuty + theHeavyDuty > MAX_VOLUNTEERS) {
			throw new ExceedsMaxVolunteersException();
		}
		neededVolunteers.put(WorkDuty.LIGHT, theLightDuty);
		neededVolunteers.put(WorkDuty.MEDIUM, theMediumDuty);
		neededVolunteers.put(WorkDuty.HEAVY, theHeavyDuty);

		volunteers = new HashMap<Volunteer, Job.WorkDuty>();
	}
	

	/**
	 * Gets the Park Manager associated with this park.
	 * 
	 * @return the park manager
	 */
	public ParkManager getManager() {
		return myManager;
	}

	/**
	 * Returns the title of the job.
	 * 
	 * @return returns the title of the job.
	 */
	public String getJobName() {
		return myName;
	}

	/**
	 * Returns the park job is associated with.
	 * 
	 * @return returns the park name job is associated with.
	 */
	public Park getPark() {
		return myPark;
	}

	/**
	 * Returns the description of the job.
	 * 
	 * @return returns the description of the job.
	 */
	public String getDescription() {
		return myDescription;
	}

	/**
	 * Gets the list of volunteers for this job.
	 * 
	 * @return returns the list of volunteers for this job.
	 */
	public Set<Volunteer> getVolunteers() {
		return volunteers.keySet();
	}

	/**
	 * Returns the date of the job.
	 * 
	 * @return returns the date of the job.
	 */
	public JobDateTime getStartDate() {
		return myStartDate;
	}
	
	/**
	 * Returns the date of the job.
	 * 
	 * @return returns the date of the job.
	 */
	public JobDateTime getEndDate() {
		return myEndDate;
	}

	/**
	 * Returns the start time of the job.
	 * 
	 * @return returns the date of the job.
	 */
	public JobDateTime getStartTime() {
		return myStartTime;
	}

	/**
	 * Returns the end time of the job.
	 * 
	 * @return returns the date of the job.
	 */
	public JobDateTime getEndTime() {
		return myEndTime;
	}

	/**
	 * Adds a volunteer to the list of volunteers for this job.
	 * 
	 * @param theVolunteer the volunteer to be added.
	 * @param theWorkDuty the specified work duty.
	 * @return true if the job's max volunteers has not been exceeded and the work duty
	 * offered by the volunteer is needed, false otherwise.
	 * @throws NullPointerException if work duty passed in is null
	 */
	public boolean addVolunteer(Volunteer theVolunteer, WorkDuty theWorkDuty) {
		final boolean wasAdded;
		if (volunteers.size() < MAX_VOLUNTEERS && volunteers.values().stream().filter(x -> x.equals(theWorkDuty))
				.count() < neededVolunteers.get(theWorkDuty)) {
			volunteers.put(theVolunteer, theWorkDuty);
			wasAdded = true;
		} else {
			wasAdded = false;
		}
		return wasAdded;
	}
	/**
	 * Determines the equality of two jobs by looking at location and job name
	 * 
	 * @returns True if job location and name are the same, false otherwise. 
	 */
	@Override
	public boolean equals(final Object theObject) {
		return (theObject instanceof Job) && (myName.equals(((Job) theObject).myName))
				&& (myPark.equals(((Job) theObject).myPark));
	}

	/**
	 * This represents different work categories that can be used.
	 */
	public enum WorkDuty {
		LIGHT, MEDIUM, HEAVY;
	}

	@Override
	public String getKey() {
		return myName + myPark.getKey();
	}

	@Override
	public int hashCode() {
		return Objects.hash(myName, myPark);
	}

	public boolean hasMaxVolunteers() {
		return volunteers.size() == MAX_VOLUNTEERS || volunteers.size() == getNumberOfPossibleVolunteers();
	}
	
	/**
	 * Returns whether or not the work duty offered by the Volunteer is needed for this job.
	 * 
	 * @param theWorkDuty the workload the volunteer is volunteering for.
	 * @return true if the workload is needed, false otherwise.
	 * @throws NullPointerException if work duty passed in is null
	 */
	public boolean needsWorkDuty(WorkDuty theWorkDuty) {
		return volunteers.values().stream().filter(x -> x.equals(theWorkDuty)).count() < neededVolunteers.get(theWorkDuty);
	}

	/**
	 * Returns the number of possible volunteers (or all work duties).
	 * @return the number of possible volunteers
	 */
	public int getNumberOfPossibleVolunteers() {
		return neededVolunteers.get(WorkDuty.LIGHT)
				+ neededVolunteers.get(WorkDuty.MEDIUM)
				+ neededVolunteers.get(WorkDuty.HEAVY);
	}

}
