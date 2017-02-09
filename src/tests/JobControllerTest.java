/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.JobController;
import model.Job;
import model.Park;


/**
 * Test suite for {@link JobController}
 * @author Mohamed Mohamed 
 * @version 1.0
 */
public class JobControllerTest
{

    /**
     * JobController objects used for testing.
     */
    private JobController myJobController;
    
    
    @Before
    public void setUp() {
        myJobController = new JobController();
        myJobController.clear();
    }
    
    @After
    public void cleanup() {
        myJobController.clear();
    }

    /**
     * Test method for {@link controller.JobController#getUpcomingJobs()}.
     */
    @Test
    public void testGetUpcomingJobs()
    {
        final ArrayList<Job> jobs = new ArrayList<Job>();
        for (int i = 0; i < 100; i++)
        {
        	final Job job = new Job("Trail Clearing" + i, new Park("BirchCreek Park", "address"), new Date(),  new Date(),  new Date(), 
                    "This job involves a lot of walking", i, 2, 4);
            jobs.add(job);
            myJobController.addJob(job);
        }
        final ArrayList<Job> allJobs = myJobController.getUpcomingJobs();
        assertTrue(allJobs.containsAll(jobs) && jobs.containsAll(jobs));
    }
    
    /**
     * Test method for {@link controller.JobController#getUpcomingJobs()}.
     */
    @Test
    public void testGetUpcomingJobsWhenEmpty()
    {
        final ArrayList<Job> allJobs = (ArrayList<Job>) myJobController.getUpcomingJobs();
        assertTrue(allJobs != null && allJobs.isEmpty());
    }

    /**
     * Test method for {@link controller.JobController#getJob(java.lang.String)}.
     * Also tests addJob.
     */
    @Test
    public void testGetJob()
    {
        final Job expectedJob = new Job("Trail Clearing", new Park("BirchCreek Park", "address"), new Date(), new Date(), new Date(), 
                                        "This job involves a lot of walking", 1, 2, 4);
        myJobController.addJob(expectedJob);
        final Job job = myJobController.getJob(expectedJob.getKey());
        assertEquals(expectedJob, job);
    }



}
