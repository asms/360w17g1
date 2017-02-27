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
import model.Job.WorkDuty;
import model.JobController;
import model.JobDate;
import model.Park;
import model.ParkController;
import model.ParkManager;
import model.Volunteer;


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

    }
    
    @After
    public void cleanup() {
        myJobController.clear();
    }

    /**
     * Test method for {@link controller.JobController#getUpcomingJobs()}.
     */

    @Test
    public void GetUpcomingJobs_NoJobsAtAll_ExpectedReturnEmptyList()
    {
        final ArrayList<Job> allUpcomingJobs = (ArrayList<Job>) myJobController.getUpcomingJobs();
        assertTrue(allUpcomingJobs != null && allUpcomingJobs.isEmpty());
    }
    
    /**
     * Test method for {@link controller.JobController#getUpcomingJobs()}.
     */
    @Test
    public void GetUpcomingJobs_NoUpcomingJobs_ExpectedReturnEmptyList()
    {
        for (int i = 1; i < 30; i++)
        {
            JobDate date = new JobDate();
            final Job job = new Job(new ParkManager("pm"), "Trail Clearing" + i, new Park("BirchCreek Park", "address"), date,  date,  date, 
                    "This job involves a lot of walking", 1, 2, 4);
            myJobController.addJob(job);
        }
        final List<Job> UpcomingJobs = myJobController.getUpcomingJobs();
        
        assertTrue(UpcomingJobs.isEmpty());
    }
    
    /**
     * Test method for {@link controller.JobController#getUpcomingJobs()}.
     */
    @Test
    public void GetUpcomingJobs_OnlyUpcomingJobs_ExpectedReturnAllJobs()
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
        final List<Job> upcomingJobs = myJobController.getUpcomingJobs();
        
        assertTrue(upcomingJobs.containsAll(jobs));
    }
    
    /**
     * Test method for {@link controller.JobController#getUpcomingJobs()}.
     */
    @Test
    public void GetUpcomingJobs_MixOfUpcomingAndPastJobs_ExpectedReturnOnlyUpcomingJobs()
    {
        JobDate jobDate;
        final ArrayList<Job> jobs = new ArrayList<Job>();
        for (int i = 1; i < 30; i++)
        {
            if(i % 2 == 0){
                jobDate = new JobDate().addDays(i);
                final Job job = new Job(new ParkManager("pm"), "Trail Clearing" + i, new Park("BirchCreek Park", "address"), jobDate,  jobDate,  jobDate, 
                                        "This job involves a lot of walking", i, 2, 4);
                jobs.add(job);
                myJobController.addJob(job);
            }
            else{
                jobDate = new JobDate();
                final Job job = new Job(new ParkManager("pm"), "Trail Clearing" + i, new Park("BirchCreek Park", "address"), jobDate,  jobDate,  jobDate, 
                                       "This job involves a lot of walking", i, 2, 4);
                myJobController.addJob(job);
            }
                 
        }
        final List<Job> upcomingJobs = myJobController.getUpcomingJobs();
        
        assertTrue(upcomingJobs.containsAll(jobs));
    }
    

    /**
     * Test method for {@link controller.JobController#getJob(java.lang.String)}.
     * Also tests addJob.
     */
    @Test
    public void GetJob_StoredJob_ExpectedCorrectJobReturned()
    {
        final Job expectedJob = new Job(new ParkManager("pm"), "Trail Clearing", new Park("BirchCreek Park", "address"), new JobDate(), new JobDate(), new JobDate(), 
                                        "This job involves a lot of walking", 1, 2, 4);
        myJobController.addJob(expectedJob);
        final Job job = myJobController.getJob(expectedJob.getKey());
        assertEquals(expectedJob, job);
    }
    
    /**
     * Test method for {@link controller.JobController#getPastJobs()}.
     */

    @Test
    public void GetPastJobs_NoJobsAtAll_ExpectedReturnEmptyList()
    {
        final ArrayList<Job> pastJobs = (ArrayList<Job>) myJobController.getPastJobs();
        assertTrue(pastJobs != null && pastJobs.isEmpty());
    }
    
    /**
     * Test method for {@link controller.JobController#getPastJobs()}.
     */

    @Test
    public void GetPastJobs_NoPastJobs_ExpectedReturnEmptyList()
    {
        for (int i = 1; i < 30; i++)
        {
            JobDate date = new JobDate().addDays(i);
            final Job job = new Job(new ParkManager("pm"), "Trail Clearing" + i, new Park("BirchCreek Park", "address"), date,  date,  date, 
                    "This job involves a lot of walking", i, 2, 4);
            myJobController.addJob(job);
        }
        final List<Job> pastJobs = myJobController.getPastJobs();
        
        assertTrue(pastJobs.isEmpty());
    }
    
    /**
     * Test method for {@link controller.JobController#getPastJobs()}.
     */

    @Test
    public void GetPastJobs_OnlyPastJobs_ExpectedListOfAllJobs()
    {
        final ArrayList<Job> jobs = new ArrayList<Job>();
        for (int i = 1; i < 30; i++)
        {
            JobDate date = new JobDate();
            final Job job = new Job(new ParkManager("pm"), "Trail Clearing" + i, new Park("BirchCreek Park", "address"), date,  date,  date, 
                    "This job involves a lot of walking", 1, 2, 4);
            myJobController.addJob(job);
            jobs.add(job);
        }
        final List<Job> pastJobs = myJobController.getPastJobs();
        
        assertTrue(pastJobs.containsAll(jobs));
    }
    
    /**
     * Test method for {@link controller.JobController#getPastJobs()}.
     */

    @Test
    public void GetPastJobs_MixOfPastJobsAndPendingJobs_ExpectedOnlyListOfPastJobs()
    {
        JobDate jobDate;
        final ArrayList<Job> jobs = new ArrayList<Job>();
        for (int i = 1; i < 30; i++)
        {
            if(i % 2 == 0){
                jobDate = new JobDate().addDays(i);
                final Job job = new Job(new ParkManager("pm"), "Trail Clearing" + i, new Park("BirchCreek Park", "address"), jobDate,  jobDate,  jobDate, 
                                        "This job involves a lot of walking", i, 2, 4);
                
                myJobController.addJob(job);
            }
            else{
                jobDate = new JobDate();
                final Job job = new Job(new ParkManager("pm"), "Trail Clearing" + i, new Park("BirchCreek Park", "address"), jobDate,  jobDate,  jobDate, 
                                       "This job involves a lot of walking", i, 2, 4);
                jobs.add(job);
                myJobController.addJob(job);
            }    
        }
        final List<Job> pastJobs = myJobController.getPastJobs();
        
        assertTrue(pastJobs.containsAll(jobs));
    }

    
	@Test
	public void isLessThanMaxJobsOnThisDate_NoStoredJobs_ExpectedTrue() {	
		JobDate date = new JobDate().addDays(5);			
		assertTrue(myJobController.isLessThanMaxJobsOnThisDate(date));
	}
	
	@Test
	public void isLessThanMaxJobsOnThisDate_AlreadyMaxJobsOnRequestedDay_ExpectedFalse() {
        JobDate date = new JobDate();
        final Job firstJob = new Job(new ParkManager("pm"), "Trail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                "This job involves a lot of walking", 1, 2, 4);

        final Job secondJob =  new Job(new ParkManager("pm"), "Rail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                "This job involves a lot of walking", 1, 2, 4);
        
        final Job thirdJob =  new Job(new ParkManager("pm"), "Raking", new Park("BirchCreek Park", "address"), date, date, date, 
                                       "This job involves a lot of walking", 1, 2, 4);
        
        final Job fourthJob =  new Job(new ParkManager("pm"), "Planting", new Park("BirchCreek Park", "address"), date, date, date, 
                                       "This job involves a lot of walking", 1, 2, 4);
        
        myJobController.addJob(firstJob);
        myJobController.addJob(secondJob);
        myJobController.addJob(thirdJob);
        myJobController.addJob(fourthJob);
        
        assertFalse(myJobController.isLessThanMaxJobsOnThisDate(date));
    }
	
	@Test
	public void isLessThanMaxJobsOnThisDate_LessThanMaxJobsOnRequestedDate_ExpectedTrue() {
	    JobDate date = new JobDate();
        final Job firstJob = new Job(new ParkManager("pm"), "Trail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                "This job involves a lot of walking", 1, 2, 4);

        final Job secondJob =  new Job(new ParkManager("pm"), "Rail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                "This job involves a lot of walking", 1, 2, 4);
        
        final Job thirdJob =  new Job(new ParkManager("pm"), "Raking", new Park("BirchCreek Park", "address"), date, date, date, 
                                       "This job involves a lot of walking", 1, 2, 4);
        myJobController.addJob(firstJob);
        myJobController.addJob(secondJob);
        myJobController.addJob(thirdJob);

        assertTrue(myJobController.isLessThanMaxJobsOnThisDate(date));
    }
	

	@Test
    public void CanAddWithNameAtPark_JobNameAlreadyExists_ExpectedFalse() {
	    
	    JobDate date = new JobDate();
	    Park park = new Park("Springwood Park", "Kent");

	    
	    final Job firstJob = new Job(new ParkManager("pm"), "Trail Clearing", park, date, date, date, 
	                                "This job involves a lot of walking", 1, 2, 4);

        myJobController.addJob(firstJob);
        assertFalse(myJobController.canAddWithNameAtPark("Trail Clearing", park));
    }
	
	@Test
    public void CanAddWithNameAtPark_JobNameDoesntAlreadyExistAtPark_ExpectedTrue() {
	    JobDate date = new JobDate();
        Park park = new Park("Springwood Park", "Kent");

        
        final Job firstJob = new Job(new ParkManager("pm"), "Trail Clearing", park, date, date, date, 
                                    "This job involves a lot of walking", 1, 2, 4);

        myJobController.addJob(firstJob);
        assertTrue(myJobController.canAddWithNameAtPark("Rail Clearing", park));
        
    }
	
	@Test (expected = NullPointerException.class)
    public void volunteerCanSignUpOnDate_NullWorkDuty(){
        final Volunteer eli = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
        JobDate date = new JobDate().addDays(5);
        final Job firstJob = new Job(new ParkManager("pm"), "Trail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                                        "This job involves a lot of walking", 1, 2, 4);
        myJobController.addJob(firstJob);
        firstJob.addVolunteer(eli, WorkDuty.LIGHT);
        eli.addJob(firstJob);
        myJobController.volunteerCanSignUpOnDate(eli, null);
    }
	
	@Test
	public void volunteerCanSignUpOnDate_AlreadyJobOnThatDate_ExpectedFalse(){
	    final Volunteer eli = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
	    JobDate date = new JobDate().addDays(5);
	    final Job firstJob = new Job(new ParkManager("pm"), "Trail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
	                                    "This job involves a lot of walking", 1, 2, 4);
	    myJobController.addJob(firstJob);
	    firstJob.addVolunteer(eli, WorkDuty.LIGHT);
	    eli.addJob(firstJob);
	    assertFalse(myJobController.volunteerCanSignUpOnDate(eli, date));
	}
	
	@Test
    public void volunteerCanSignUpOnDate_NoJobOnThatDate_ExpectedTrue(){
	    final Volunteer eli = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
        JobDate date = new JobDate().addDays(5);
        final Job firstJob = new Job(new ParkManager("pm"), "Rail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                                        "This job involves a lot of walking", 1, 2, 4);
        myJobController.addJob(firstJob);
        eli.addJob(firstJob);
        assertTrue(myJobController.volunteerCanSignUpOnDate(eli, date.addDays(5)));
    }
	
	@Test (expected = NullPointerException.class)
    public void assertSigningUp_NullParam(){
        final Volunteer eli = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
        
        myJobController.assertSigningUp(eli, null);
    }
    
	@Test (expected = IllegalStateException.class)
    public void assertSigningUp_ConflictingJobDates(){
	    final Volunteer eli = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
        JobDate date = new JobDate().addDays(5);
        final Job firstJob = new Job(new ParkManager("pm"), "Trail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                                        "This job involves a lot of walking", 1, 2, 4);
        myJobController.addJob(firstJob);
        firstJob.addVolunteer(eli, WorkDuty.LIGHT);
        eli.addJob(firstJob);
        myJobController.assertSigningUp(eli, firstJob);
    }
	
	@Test (expected = IllegalStateException.class)
    public void assertSigningUp_JobHasMaxVolunteers(){
	    JobDate date = new JobDate().addDays(5);
	    final Job jobWithMaxVolunteers = new Job(new ParkManager("pm"), "Trail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                                     "This job involves a lot of walking", 30, 0, 0);
	    final Volunteer jackson = new Volunteer("jackson", "Jackson", "Howard", "360-641-6734", "jackson@outlook.com");
        
        final Volunteer[] volunteers = {
                new Volunteer("jake",   "Jake",     "Smith",    "253-111-1234", "jake@uw.edu"),
                new Volunteer("wendy",  "Wendy",    "Anderson", "253-112-7541", "wendy@uw.edu"),
                new Volunteer("sara",   "Sara",     "Anderson", "253-113-1271", "sara@uw.edu"),
                new Volunteer("alex",   "Alex",     "Anderson", "253-114-7824", "alex@uw.edu"),
                new Volunteer("paul",   "Paul",     "Anderson", "253-115-4285", "paul@gmail.com"),
                new Volunteer("ethan",  "Ethan",    "Anderson", "253-116-1365", "ethan@gmail.com"),
                new Volunteer("monica", "Monica",   "Anderson", "253-117-3466", "asdf@yahoo.com"),
                new Volunteer("joey",   "Joey",     "Anderson", "253-118-6543", "space@yahoo.com"),
                new Volunteer("edward", "Edward",   "Johnson",  "253-119-7654", "word@yahoo.com"),
                new Volunteer("alan",   "Alan",     "Johnson",  "253-110-4567", "name@yahoo.com"),
        };
        for (int i = 0; i < volunteers.length; i++){
            jobWithMaxVolunteers.addVolunteer(volunteers[i], WorkDuty.LIGHT);
        }
        myJobController.assertSigningUp(jackson, jobWithMaxVolunteers);
    }
	
	@Test 
    public void assertSigningUp_VolunteerCanSignUp_ExpectedTrue(){
	    final Volunteer eli = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
        JobDate date = new JobDate().addDays(5);
        final Job job = new Job(new ParkManager("pm"), "Trail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                                        "This job involves a lot of walking", 1, 2, 4);
        assertTrue(myJobController.assertSigningUp(eli, job));
    }
	
	
   @Test (expected = NullPointerException.class)
    public void signUp_NullWorkDuty(){
        final Volunteer eli = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
        JobDate date = new JobDate().addDays(5);
        final Job job = new Job(new ParkManager("pm"), "Trail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                                        "This job involves a lot of walking", 1, 2, 0);
        myJobController.signUp(eli, job, null);
    }
	
   @Test (expected = NullPointerException.class)
   public void signUp_NullJob(){
       final Volunteer eli = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
       myJobController.signUp(eli, null, WorkDuty.LIGHT);
   }
   
	@Test (expected = IllegalStateException.class)
	public void signUp_VolunteerTypeIsNotNeeded(){
	    final Volunteer eli = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
        JobDate date = new JobDate().addDays(5);
        final Job job = new Job(new ParkManager("pm"), "Trail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                                        "This job involves a lot of walking", 1, 2, 0);
        myJobController.signUp(eli, job, WorkDuty.HEAVY);
	}
	
	@Test
    public void signUp_VolunteerTypeIsNeeded_ExpectedJobIsInVolunteersUpcomingJobsList(){
	    final Volunteer eli = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
        JobDate date = new JobDate().addDays(5);
        final Job job = new Job(new ParkManager("pm"), "Trail Clearing", new Park("BirchCreek Park", "address"), date, date, date, 
                                        "This job involves a lot of walking", 1, 2, 1);
        myJobController.signUp(eli, job, WorkDuty.HEAVY);
        job.addVolunteer(eli, WorkDuty.HEAVY);
        
        assertTrue(job.getVolunteers().contains(eli));
    }
	 

}
