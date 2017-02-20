/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Job model class.
 * 
 * @author Mohamed Mohamed
 * @version 1.0
 */
public class Job implements UniqueObject {
	/**
	 * Generated serial version id.
	 */
	private static final long serialVersionUID = -4115864921352899827L;

	/**
	 * BR: A maximum of 30 volunteers for any job.
	 */
	public static final int MAX_VOLUNTEERS = 30;

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
	 * The date of the job.
	 */
	private Date myDate;

	/**
	 * The start time of the job.
	 */
	private Date myStartTime;

	/**
	 * The end time of the job.
	 */
	private Date myEndTime;

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
	 */
	public Job(final ParkManager theManager, final String theName, final Park thePark, final Date theDate,
			final Date theStartTime, final Date theEndTime, final String theDescription, final int theLightDuty,
			final int theMediumDuty, final int theHeavyDuty) {
		myManager = Objects.requireNonNull(theManager);
		myName = Objects.requireNonNull(theName);

		myPark = Objects.requireNonNull(thePark);

		myDate = Objects.requireNonNull(theDate);

		myStartTime = Objects.requireNonNull(theStartTime);

		myEndTime = Objects.requireNonNull(theEndTime);

		myDescription = Objects.requireNonNull(theDescription);

		neededVolunteers = new HashMap<WorkDuty, Integer>();
		neededVolunteers.put(WorkDuty.LIGHT, theLightDuty);
		neededVolunteers.put(WorkDuty.MEDIUM, theMediumDuty);
		neededVolunteers.put(WorkDuty.HEAVY, theHeavyDuty);

		volunteers = new HashMap<Volunteer, Job.WorkDuty>();
	}

	/**
	 * A copy constructor that creates a copy of the existing job.
	 * 
	 * @param job
	 *            job to be cloned.
	 */
	public Job(Job job) {
		this(job.getManager(), job.getJobName(), job.getPark(), job.getDate(), job.getStartTime(), job.getEndTime(),
				job.getDescription(), job.neededVolunteers.get(WorkDuty.LIGHT),
				job.neededVolunteers.get(WorkDuty.MEDIUM), job.neededVolunteers.get(WorkDuty.HEAVY));
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
	public Date getDate() {
		return myDate;
	}

	/**
	 * Returns the start time of the job.
	 * 
	 * @return returns the date of the job.
	 */
	public Date getStartTime() {
		return myStartTime;
	}

	/**
	 * Returns the end time of the job.
	 * 
	 * @return returns the date of the job.
	 */
	public Date getEndTime() {
		return myEndTime;
	}

	/**
	 * Adds a volunteer to the list of volunteers for this job.
	 * 
	 * @param theVolunteer
	 *            the volunteer to be added.
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
		return myName + myPark;
	}

	@Override
	public int hashCode() {
		return Objects.hash(myName, myPark);
	}

	public boolean hasMaxVolunteers() {
		return volunteers.size() == MAX_VOLUNTEERS || volunteers.size() == neededVolunteers.size();
	}

}
