/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Job;
import model.Job.WorkDuty;
import model.Park;
import model.Volunteer;

/**
 * Simulates a database for jobs.
 * @author Mohamed Mohamed
 * @version 1.0
 */
public class JobController extends AbstractController<Job> {
	
	public static final int MAX_JOBS_PER_DAY = 2;
	public static final int DEFAULT_MAX_PENDING_JOBS = 30;
	public static final int MAX_FUTURE_DATE_MONTHS_FROM_NOW_FOR_JOB_CREATION = 1;
	public static final int MIN_FUTURE_DATE_DAYS_FROM_NOW_FOR_JOB_SIGNUP = 3;
	
	private int myMaximumNumberOfPendingJobs;
    
    public JobController() {
    	final Integer maxPendingJobs = deserializeFromDisk(getClass().getSimpleName() + "MAX_PENDING_JOBS", Integer.class);
    	if (maxPendingJobs == null) {
    		myMaximumNumberOfPendingJobs = DEFAULT_MAX_PENDING_JOBS;
    	} else {
    		myMaximumNumberOfPendingJobs = maxPendingJobs.intValue();
    	}
    }
	
	/**
     * Returns a list of all jobs.
     * 
     * @return all jobs.
     */
    public List<Job> getAllJobs() {
        return new ArrayList<Job>(myList.values());
    }
    
    /**
     * Returns all jobs that are after the current date.
     * @return all upcoming jobs
     */
    public List<Job> getUpcomingJobs()
    {
    	return new ArrayList<Job>(filterUpcomingJobs(myList.values().stream()).collect(Collectors.toList()));
    }
    
    public List<Job> getPastJobs() {
    	return new ArrayList<Job>(filterPastJobs(myList.values().stream()).collect(Collectors.toList()));
    }
    
    /**
     * Selects only the upcoming jobs from a stream of jobs.
     * @param theStream the stream of jobs
     * @return a steam of upcoming jobs
     */
    public static Stream<Job> filterUpcomingJobs(final Stream<Job> theStream) {
    	return theStream.filter(x -> new JobDate().compareTo(x.getDate()) < 0);
    }
    
    /**
     * Selects only the jobs a volunteer can sign up for.
     * @param theStream the stream of jobs
     * @return a stream of upcoming jobs
     */
    public static Stream<Job> filterAtLeastThreeDaysAheadandNoSameDayConflict(final Stream<Job> theStream) {
        JobDate futureDate = new JobDate().getStartOfDate().addDays(MIN_FUTURE_DATE_DAYS_FROM_NOW_FOR_JOB_SIGNUP);
        return theStream.filter(x -> x.getDate().after(futureDate) || x.getDate().equals(futureDate));
    }
    
    /**
     * Selects only the past jobs from a stream of jobs.
     * @param theStream the stream of jobs
     * @return a steam of past jobs
     */
    public static Stream<Job> filterPastJobs(final Stream<Job> theStream) {
    	return theStream.filter(x -> new JobDate().compareTo(x.getDate()) >= 0);
    }
    
    
    /**
     * Returns a job with a specific key.
     * @param theName the name of a job
     * @return the job that has the specified name
     */
    public Job getJob(final String theKey) 
    {
        return myList.get(theKey);
    }
    
    
    /**
     * Adds a job to the database.
     * @param theJob the job to be added.
     */
    public void addJob(Job theJob)
    {
        add(theJob);
    }

    /**
     * @param theDate
     * @return True if the Job can be added for that date
     */
	public boolean canAddWithDate(final JobDate theDate) {
		return myList.entrySet().stream().filter(x -> x.getValue().getDate().equals(theDate)).count() < MAX_JOBS_PER_DAY;
	}
	
	public boolean canAddWithNameAtPark(final String theName, final Park thePark) {
		return !myList.containsKey(theName + thePark);
	}
	

	public boolean volunteerCanSignUpOnDate(final Volunteer theVolunteer, final JobDate theDate) {
		return getUpcomingJobs().stream().filter(x -> x.getVolunteers().contains(theVolunteer)
														&& JobDate.sameDates(x.getDate(), theDate)).count() == 0;
	}
	
	/**
	 * Asserts that a volunteer wants to sign up for a job.
	 * @param theVolunteer a volunteer
	 * @param theJob the job that the volunteer wants to sign up for
	 * @return true if the volunteer can sign up for the job
	 * @throws IllegalStateException if the volunteer cannot sign up for the job
	 */
	public boolean assertSigningUp(final Volunteer theVolunteer, final Job theJob) throws IllegalStateException {
		boolean canSignUp = false;
		if (theVolunteer.isBlackballed()) {
			throw new IllegalStateException("Blackballed");
		} else if (!volunteerCanSignUpOnDate(theVolunteer, theJob.getDate())) {
			throw new IllegalStateException("Preoccupied");
		} else if(theJob.hasMaxVolunteers()) {
			throw new IllegalStateException("JobFull");
		} else {
			canSignUp = true;
		}
		return canSignUp;
	}
	
	public void signUp(final Volunteer theVolunteer, final Job theJob, final WorkDuty theWorkDuty) {
		if (assertSigningUp(theVolunteer, theJob) && theJob.needs(theWorkDuty) && theJob.addVolunteer(theVolunteer, theWorkDuty)) {
			addJob(theJob);
		} else {
			throw new IllegalStateException("WorkerNotNeeded");
		}
	}
	
	/**
	 * Returns the maximum number of pending jobs.
	 * <p>BR: Not more than the maximum number of pending jobs at a time.</p>
	 * @return the maximum number of pending jobs
	 */
	public int getMaximumNumberOfPendingJobs() {
		return myMaximumNumberOfPendingJobs;
	}

	public List<Job> getByPark(final Park park) {
		return myList.values().stream().filter(x -> x.getPark().equals(park)).collect(Collectors.toList());
	}

	/**
	 * Sets the maximum number of pending jobs.
	 * @param theMaxNumber the maximum number of pending jobs
	 */
	public void setMaximumNumberOfPendingJobs(int theMaxNumber) {
		myMaximumNumberOfPendingJobs = theMaxNumber;
		serializeToDisk(getClass().getSimpleName() + "MAX_PENDING_JOBS", Integer.valueOf(myMaximumNumberOfPendingJobs));
	}
	
	@Override
	public void clear() {
		super.clear();
		setMaximumNumberOfPendingJobs(DEFAULT_MAX_PENDING_JOBS);
	
	}
}
