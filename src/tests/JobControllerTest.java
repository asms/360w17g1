/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import model.Job;
import model.JobController;
import model.JobDate;
import model.Park;
import model.ParkManager;


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
        for (int i = 1; i < 30; i++)
        {
        	JobDate date = new JobDate().addDays(i);
        	final Job job = new Job(new ParkManager("pm"), "Trail Clearing" + i, new Park("BirchCreek Park", "address"), date,  date,  date, 
                    "This job involves a lot of walking", i, 2, 4);
            jobs.add(job);
            myJobController.addJob(job);
        }
        final List<Job> allJobs = myJobController.getUpcomingJobs();
        
        assertTrue(allJobs.containsAll(jobs));
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
        final Job expectedJob = new Job(new ParkManager("pm"), "Trail Clearing", new Park("BirchCreek Park", "address"), new JobDate(), new JobDate(), new JobDate(), 
                                        "This job involves a lot of walking", 1, 2, 4);
        myJobController.addJob(expectedJob);
        final Job job = myJobController.getJob(expectedJob.getKey());
        assertEquals(expectedJob, job);
    }


	@Test
	public void testGetAllJobs_NoJobs() {
		//JobController myJobController1 = new JobController();
		
		assertTrue(myJobController.getAllJobs().isEmpty());
		
	}
    
	/**
	 * TODO: Fix this test method. You can not add a job to the current date. Volunteers would not have time to sign up.
	 */
	@Test
	public void testCanAddWithDate() {
		
		JobDate date = new JobDate();
		final Job firstJob = new Job(new ParkManager("pm"), "Trail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                "This job involves a lot of walking", 1, 2, 4);

		final Job anotherJob =  new Job(new ParkManager("pm"), "Rail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                "This job involves a lot of walking", 1, 2, 4);
		
		
		assertTrue(myJobController.canAddWithDate(date));
		myJobController.addJob(firstJob);
		assertTrue(myJobController.canAddWithDate(date));
		myJobController.addJob(anotherJob);
		assertFalse(myJobController.canAddWithDate(date));
	}
	
	@Test
	public void testCanAddWithNameAtPark() {
		
		
	}
	
	@Test
	public void testGetMaximumNumberOfPendingJobs_WithMax() {
		int previous = myJobController.getMaximumNumberOfPendingJobs(); //store previous value
		
		myJobController.setMaximumNumberOfPendingJobs(previous + 1);
		myJobController = new JobController();
		assertTrue(myJobController.getMaximumNumberOfPendingJobs() == previous + 1);
		
		myJobController.setMaximumNumberOfPendingJobs(previous); //restore previous value
	}
    
	
	@Test
	public void testDefaultGetMaximumNumberOfPendingJobs() {
		myJobController.clear();
		assertTrue(myJobController.getMaximumNumberOfPendingJobs() == JobController.DEFAULT_MAX_PENDING_JOBS);
	}
    
}
