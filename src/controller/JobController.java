/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */

package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Job;

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
	
	// stores a copy of all jobs in the system. 
    private static ArrayList<Job> allJobs;
	
	/**
     * Returns a list of all jobs.
     * 
     * @return all jobs.
     */
    public static List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<Job>();
        for (Job job : allJobs) {
            jobs.add(new Job(job));
        }
        return jobs;
    }
    
    /**
     * Returns all upcoming jobs
     * @return all upcoming jobs
     */
    public ArrayList<Job> getUpcomingJobs()
    {
        return new ArrayList<Job>(myList.values());
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
	
	public boolean canAddWithNameAtPark(final String theName, final String theParkName) {
		return !myList.containsKey(theName + theParkName);
	}



}