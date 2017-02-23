package tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.JobController;
import model.JobDate;
import model.Job;
import model.Job.WorkDuty;
import model.Park;
import model.ParkManager;

public class JobTest {
    
	JobController pk = new JobController();
	
    /** This represents different jobs for testing. */
    private Job testJob1;
    
    /** This represents volunteers sign up for light work category. */
    private Map<String, WorkDuty> volunteerLight;
    
    /** This represents volunteers sign up for medium work category. */
    private Map<String, WorkDuty> volunteerMed;
    
    /** This represents volunteers sign up for heavy work category. */
    private Map<String, WorkDuty> volunteerHeavy;
    
    /**
     * Initialize the objects.
     *
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        
    	
    	final JobDate date = new JobDate();
        testJob1 = new Job(new ParkManager("pm"), "Park Cleanup", new Park("Cherry Park", "Park Location"),  date, date, date, "Cleaning up litter", 1, 3, 5);
        
        //represent volunteers sign up for light work category
        volunteerLight = new HashMap<String, WorkDuty>();
        volunteerLight.put("volunteerLight", WorkDuty.LIGHT);
        
        //represent volunteers sign up for medium work category
        volunteerMed = new HashMap<String, WorkDuty>();
        volunteerMed.put("volunteerMedium", WorkDuty.MEDIUM);
        
        //represent volunteers sign up for heavy work category
        volunteerHeavy = new HashMap<String, WorkDuty>();
        volunteerHeavy.put("volunteerHeavy", WorkDuty.HEAVY);
    }
    
    @Test
    public void testNotEqualDifferentParkName() {
    	final Job differentJobName = new Job(new ParkManager("pm"), "Different Job Name", new Park("Cherry Park", "Park Location"),  new JobDate(), new JobDate(), new JobDate(), "Cleaning up litter", 1, 3, 5);
        assertFalse(differentJobName.getJobName().equals(testJob1.getJobName()));
    }
    
    @Test
    public void testOnVolunteerSignedUpForLight() {
        assertEquals("Volunteer who signed up for Light",
                     volunteerLight.get("volunteerLight"), WorkDuty.LIGHT);
    }
    
    @Test
    public void testOnVolunteerSignedUpForMedium() {
        assertEquals("Volunteer who signed up for Medium",
                     volunteerMed.get("volunteerMedium"), WorkDuty.MEDIUM);
    }
    
    @Test
    public void testOnVolunteerSignedUpForHeavy() {
        assertEquals("Volunteer who signed up for Heavy",
                     volunteerHeavy.get("volunteerHeavy"), WorkDuty.HEAVY);
    }
    
    
    @Test
    public void testGetVolunteers_VolunteersInList() {
    	Job job = new Job(new ParkManager("pm"), "PARKNAM", new Park("Park", "Location"), new JobDate(), new JobDate(), new JobDate(), "description", 1,2,3);
    	pk.addJob(job);
    	//System.out.println(pk.getUpcomingJobs());
    	
    }
}
