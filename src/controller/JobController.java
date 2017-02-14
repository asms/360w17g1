/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Job;
import model.Park;

/**
 * Simulates a database for jobs.
 * @author Mohamed Mohamed
 * @version 1.0
 */
public class JobController extends AbstractController<Job> {
	
	/**
	 * BR: There can be no more than two jobs on any given day.
	 */
	public static final int MAX_JOBS_PER_DAY = 2;
	
	/**
	 * BR: There can be no more than two jobs on any given day.
	 */
	public static final int DEFAULT_MAX_PENDING_JOBS = 30;
	
	private int myMaximumNumberOfPendingJobs = 30;
    
    public JobController() {
    	myMaximumNumberOfPendingJobs = DEFAULT_MAX_PENDING_JOBS;
    	// all jobs can be accessed my referencing the myList variable
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
    	return theStream.filter(x -> new Date().compareTo(x.getDate()) < 0);
    }
    
    /**
     * Selects only the jobs a volunteer can sign up for.
     * @param theStream the stream of jobs
     * @return a stream of upcoming jobs
     */
    public static Stream<Job> filterAtLeastThreeDaysAheadandNoSameDayConflict(final Stream<Job> theStream) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 3); 
        return theStream.filter(x -> x.getDate().after(c.getTime()) || x.getDate().equals(c));
    }
    
    /**
     * Selects only the past jobs from a stream of jobs.
     * @param theStream the stream of jobs
     * @return a steam of past jobs
     */
    public static Stream<Job> filterPastJobs(final Stream<Job> theStream) {
    	return theStream.filter(x -> new Date().compareTo(x.getDate()) >= 0);
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

	public boolean canAddWithDate(final Date theDate) {
		return myList.entrySet().stream().filter(x -> x.getValue().getDate().equals(theDate)).count() < MAX_JOBS_PER_DAY;
	}
	
	public boolean canAddWithNameAtPark(final String theName, final Park thePark) {
		return !myList.containsKey(theName + thePark);
	}
	
	/**
	 * Returns the maximum number of pending jobs.
	 * <p>BR: Not more than the maximum number of pending jobs at a time.</p>
	 * @return the maximum number of pending jobs
	 */
	public int getMaximumNumberOfPendingJobs() {
		return myMaximumNumberOfPendingJobs; //TODO: Make persistent and dynamic, changed by staff member
	}

	public List<Job> getByPark(final Park park) {
		return myList.values().stream().filter(x -> x.getPark().equals(park)).collect(Collectors.toList());
	}



}
