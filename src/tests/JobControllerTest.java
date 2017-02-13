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
        for (int i = 0; i < 29; i++)
        {
        	final Job job = new Job("Trail Clearing" + i, new Park("BirchCreek Park", "address"), new Date(),  new Date(),  new Date(), 
                    "This job involves a lot of walking", i, 2, 4);
            jobs.add(job);
            myJobController.addJob(job);
        }
        final List<Job> allJobs = myJobController.getUpcomingJobs();
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


	@Test
	public void testGetAllJobs_NoJobs() {
		//JobController myJobController1 = new JobController();
		
		assertTrue(myJobController.getAllJobs().isEmpty());
		
	}
    
	@Test
	public void testCanAddWithDate() {
		
		Date date = new Date(3/14/2017);
		final Job firstJob = new Job("Trail Clearing", new Park("BirchCreek Park", "address"), date, new Date(), new Date(), 
                "This job involves a lot of walking", 1, 2, 4);

		final Job anotherJob =  new Job("Rail Clearing", new Park("BirchCreek Park", "address"), date, new Date(), new Date(), 
                "This job involves a lot of walking", 1, 2, 4);
		
		final Job a3rdJob =  new Job("Ail Clearing", new Park("BirchCreek Park", "address"), date, new Date(), new Date(), 
                "This job involves a lot of walking", 1, 2, 4);
		
		
		myJobController.addJob(firstJob);
		assertTrue(myJobController.canAddWithDate(date));
		myJobController.addJob(anotherJob);
		assertTrue(myJobController.canAddWithDate(date));
		myJobController.addJob(a3rdJob);
		assertFalse(myJobController.canAddWithDate(date));
	}
	
	@Test
	public void testCanAddWithNameAtPark() {
		
		
	}
	
	@Test
	public void testGetMaximumNumberOfPendingJobs_WithMax() {

		assertTrue(myJobController.getMaximumNumberOfPendingJobs() == 30);
	}
    
	
	@Test
	public void testGetMaximumNumberOfPendingJobs_NotMax() {

		assertFalse(myJobController.getMaximumNumberOfPendingJobs() == 15);
	}
    
}
