/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */

package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public List<Job> getUpcomingJobs()
    {
        return new ArrayList<Job>(myList.values());
    }             
    
    
    /**
     * Returns a job with a specific name.
     * @param theName the name of a job
     * @return the job that has the specified name
     */
    public Job getJob(final String theName) 
    {
        return myList.get(theName);
    }
    
    
    /**
     * Adds a job to the database.
     * @param theJob the job to be added.
     */
    public void addJob(Job theJob)
    {
       add(theJob);
    }

	public boolean canAddToDate(final Date date) {
		return myList.entrySet().stream().filter(x -> x.getValue().getDate().equals(date)).count() < MAX_JOBS_PER_DAY;
	}



}
