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
                new Volunteer("betty",  "Betty",    "Johnson",  "253-121-1865", "betty@yahoo.com"),
                new Volunteer("bertha", "Bertha",   "Johnson",  "253-131-1451", "bertha@yahoo.com"),
                new Volunteer("mandy",  "Mandy",    "Johnson",  "253-141-1451", "moore@yahoo.com"),
                new Volunteer("roger",  "Roger",    "Johnson",  "253-151-7541", "rabbit@yahoo.com"),
                new Volunteer("paulina","Paulina",  "Johnson",  "253-161-2457", "paulina@yahoo.com"),
                new Volunteer("jose",   "Jose",     "Johnson",  "253-171-7585", "jose@yahoo.com"),
                new Volunteer("nina",   "Nina",     "Johnson",  "253-181-5368", "nina@yahoo.com"),
                new Volunteer("joe",    "Joe",      "Smith",    "253-191-4311", "smith@yahoo.com"),
                new Volunteer("joseph", "Joseph",   "Smith",    "253-176-3411", "jojo@yahoo.com"),
                new Volunteer("rambo",  "Rambo",    "Smith",    "253-121-1451", "rambo@yahoo.com"),
                new Volunteer("et",     "E.",       "T.",       "253-131-1341", "alien@yahoo.com"),
                new Volunteer("nicky",  "Nicky",    "Ross",     "253-651-1111", "nicky@yahoo.com"),
                new Volunteer("nick",   "Nick",     "Ross",     "253-341-1411", "nick@yahoo.com"),
                new Volunteer("dylan",  "Dylan",    "Lee",      "253-151-1431", "dylan@yahoo.com"),
                new Volunteer("george", "George",   "Lee",      "253-971-1111", "george@yahoo.com"),
                new Volunteer("seth",   "Seth",     "Lee",      "253-181-1311", "seth@yahoo.com"),
                new Volunteer("carly",  "Carly",    "Lee",      "253-111-1611", "carly@yahoo.com"),
                new Volunteer("sam",    "Sam",      "Lee",      "253-761-1711", "lee@yahoo.com"),
                new Volunteer("mantha", "Samantha", "Lee",      "253-451-1811", "samantha@yahoo.com"),
                new Volunteer("steph",  "Steph",    "Lee",      "253-111-1111", "steph@yahoo.com")
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
