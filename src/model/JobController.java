/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */


package model;


import java.util.ArrayList;
import java.util.List;



/**
 * Simulates a database for jobs.
 * @author Mohamed Mohamed
 * @version 1.0
 */
public class JobController extends AbstractController<Job>
{

    /**
     * Returns all upcoming jobs
     * @return all upcoming jobs
     */
    public  List<Job> getUpcomingJobs()
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



}
