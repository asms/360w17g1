/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exceptions.AllreadySignedUpForJobOnThisDateException;
import exceptions.JobFullException;
import exceptions.VolunteerWorkDutyNotNeededException;
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
	
	public static final int MAX_JOBS_PER_DAY = 4;
	public static final int DEFAULT_MAX_PENDING_JOBS = 20;
	public static final int MAX_FUTURE_DATE_DAYS_FROM_NOW_FOR_JOB_CREATION = 75;
	public static final int MIN_FUTURE_DATE_DAYS_FROM_NOW_FOR_JOB_SIGNUP = 2;
	public static final int MAX_JOB_DURATION_DAYS = 3;
	
	private int myMaximumNumberOfPendingJobs;
    
	/**
	 * Loads the previous state of the JobController.
	 */
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
     * @return the list of upcoming jobs
     */
    public List<Job> getUpcomingJobs()
    {
    	return new ArrayList<Job>(filterUpcomingJobs(myList.values().stream()).collect(Collectors.toList()));
    }
    
    /**
     * Returns all jobs that are before the current date.
     * @return the list of all past jobs
     */
    public List<Job> getPastJobs() {
    	return new ArrayList<Job>(filterPastJobs(myList.values().stream()).collect(Collectors.toList()));
    }
    
    /**
     * Filters a stream of jobs so that only the jobs after the current date remain.
     * @param theStream the stream of jobs
     * @return a steam of upcoming jobs
     */
    public static Stream<Job> filterUpcomingJobs(final Stream<Job> theStream) {
    	return theStream.filter(x -> new JobDateTime().compareTo(x.getStartDate()) < 0);
    }
    
    /**
     * Filters a stream of jobs so that only the jobs that start {@link JobController#MIN_FUTURE_DATE_DAYS_FROM_NOW_FOR_JOB_SIGNUP}
     * past the current date.
     * @param theStream the stream of jobs
     * @return a stream of upcoming jobs
     */
    public static Stream<Job> filterAtLeastMinimumDaysAhead(final Stream<Job> theStream) {
        JobDateTime futureDate = new JobDateTime().getStartOfDate().addDays(MIN_FUTURE_DATE_DAYS_FROM_NOW_FOR_JOB_SIGNUP);
        return theStream.filter(x -> x.getStartDate().after(futureDate) || x.getStartDate().equals(futureDate));
    }
    
    /**
     * Filters a stream of jobs so that only the jobs that start before the current date remain.
     * @param theStream the stream of jobs
     * @return a steam of past jobs
     */
    public static Stream<Job> filterPastJobs(final Stream<Job> theStream) {
    	return theStream.filter(x -> new JobDateTime().compareTo(x.getStartDate()) >= 0);
    }
    
    
    /**
     * Returns a job with a specific key.
     * @param theKey the key value for the job
     * @return the job that has the specified name
     */
    public Job getJob(final String theKey) {
        return myList.get(theKey);
    }
    
    
    /**
     * Adds a job to the database.
     * @param theJob the job to be added.
     */
    public void addJob(Job theJob) {
        add(theJob);
    }

    /**
     * Checks if a given date can take any more jobs
     * @param theDate the date to check.
     * @return True if the date can take more jobs, false if max jobs are on that date.
     */
    public boolean isLessThanMaxJobsOnThisDate(final JobDateTime theDate) {
    	Predicate<Job> dateSameAsJobDate = x -> {
    		return JobDateTime.intersects(theDate, theDate, x.getStartDate(), x.getEndDate());
    				};
    	
    	final long numberOfJobs = myList.values().stream().filter(dateSameAsJobDate).count();
    	return numberOfJobs < MAX_JOBS_PER_DAY;
    }
    
    /**
     * Checks if the dates within the range have less than the maximum number of jobs.
     * @param theStartDate the start date of the range
     * @param theEndDate the end date of the range
     * @return true if all jobs within the range have less than the maximum number of jobs
     */
	public boolean isLessThanMaxJobsOnThisDateRange(JobDateTime theStartDate, JobDateTime theEndDate) {
		Predicate<Job> dateSameAsJobDate = x -> JobDateTime.intersects(theStartDate, theEndDate, x.getStartDate(), x.getEndDate());
    	final long numberOfJobs = myList.values().stream().filter(dateSameAsJobDate).count();
		return numberOfJobs < MAX_JOBS_PER_DAY;
	}
    
    /**
     * Checks if job already exists at park.
     * @param theName the name of the job to be checked
     * @param thePark the park the job is going to be held at.
     * @return true if job doesn't already exist at park, false otherwise.
     */
    public boolean canAddWithNameAtPark(final String theName, final Park thePark) {
    	return !myList.containsKey(theName + thePark.getKey());
    }
    
    /**
     * Returns whether a volunteers can sign up on a date range.
     * 
     * @param theVolunteer the volunteer being looked at
     * @param theStartDate the start date
     * @param theEndDate the end date
     * @return false if volunteer is already working a job on one of the dates, true otherwise.
     */
    public boolean volunteerCanSignUpOnDateRange(final Volunteer theVolunteer, final JobDateTime theStartDate,
    		final JobDateTime theEndDate) {
    	final Predicate<Job> dateSameAsJobDateAndJobContainsVolunteer = x -> x.getVolunteers().contains(theVolunteer)
    			&& JobDateTime.intersects(theStartDate, theEndDate, x.getStartDate(), x.getEndDate());
    	return getUpcomingJobs().stream().filter(dateSameAsJobDateAndJobContainsVolunteer).count() == 0;
    }
	
	/**
	 * Asserts that a volunteer wants to sign up for a job.
	 * @param theVolunteer a volunteer
	 * @param theJob the job that the volunteer wants to sign up for
	 * @return true if the volunteer can sign up for the job
	 * @throws IllegalStateException if the volunteer cannot sign up for the job
	 * @throws NullPointerException if the job passed in is null
	 */
	public boolean assertSigningUp(final Volunteer theVolunteer, final Job theJob) {
		boolean canSignUp = false;
		if (!volunteerCanSignUpOnDateRange(theVolunteer, theJob.getStartDate(), theJob.getEndDate())) {
			throw new AllreadySignedUpForJobOnThisDateException();
		} else if(theJob.hasMaxVolunteers()) {
			throw new JobFullException();
		} else {
			canSignUp = true;
		}
		return canSignUp;
	}
	
	/**
	 * Signs up volunteer for requested job if all criteria are met.
	 * 
	 * @param theVolunteer the volunteer to be signed up
	 * @param theJob the job to sign up the volunteer for
	 * @param theWorkDuty the workload volunteer is signing up for.
	 * @throws VolunteerWorkDutyNotNeededException if volunteer of that work duty is not needed
	 * @throws NullPointerException if either job or work duty are null
	 */
	public void signUp(final Volunteer theVolunteer, final Job theJob, final WorkDuty theWorkDuty) {
		if (assertSigningUp(theVolunteer, theJob)) {
			if (theJob.needsWorkDuty(theWorkDuty) && theJob.addVolunteer(theVolunteer, theWorkDuty)) {
				addJob(theJob);
			} else {
				throw new VolunteerWorkDutyNotNeededException();
			}
			
		}
	}
	
	/**
	 * Returns the maximum number of pending jobs (for the system).
	 * @return the maximum number of pending jobs
	 */
	public int getMaximumNumberOfPendingJobs() {
		return myMaximumNumberOfPendingJobs;
	}

	/**
	 * Gets the Jobs at the park being looked at
	 * @param park the park 
	 * @return jobs in that Park.
	 */
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
	
	/**
	 * Gets the maximum future date of a new job after a specific start date.
	 * 
	 * @param theStartDate a valid start date
	 * @return the maximum future date
	 */
	public static JobDateTime getMaximumEndDateFromStartDate(JobDateTime theStartDate) {
		final JobDateTime absoluteMaxFutureDate
						= new JobDateTime().getStartOfDate().addDays(MAX_FUTURE_DATE_DAYS_FROM_NOW_FOR_JOB_CREATION);
		final JobDateTime relativeMaxNumberDaysFutureDate
										= theStartDate.getStartOfDate().addDays(JobController.MAX_JOB_DURATION_DAYS - 1);
		return JobDateTime.minDate(absoluteMaxFutureDate, relativeMaxNumberDaysFutureDate);
	}
}
