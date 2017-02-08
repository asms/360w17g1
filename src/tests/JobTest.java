package tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.Job.WorkDuty;
import model.Park;

public class JobTest {
    
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
        
        testJob1 = new Job("Park Cleanup", new Park("Cherry Park", "Park Location"),  new Date(), new Date(), new Date(), "Cleaning up litter", 1, 3, 5);
        
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
    public void testOnParkName() {
        assertEquals("Park name should be different", new Park("", ""), testJob1.getPark());
    }
    
    @Test
    public void testOnJobName() {
        assertEquals("Job name should be the same.", "Park Cleanup", testJob1.getJobName());
    }
    
    @Test
    public void testOnJobDescription() {
        assertEquals("Job Description is different", "Cleaning up litter", testJob1.getDescription());
    }
    
    /**
     * THIS TEST WILL NOT PASS BECAUSE THERE IS NO DIFFICULTY BEING SET
     * FOR THE JOB AS A WHOLE. WE WILL NEED GETTERS FOR EACH DIFFICULTY 
     * LEVEL FOR THIS TO WORK.
     */
    @Test
    public void testOnJobDifficultity() {
    	System.out.println(testJob1.getDifficulty());
        assertEquals("Job Difficulity should be the same.", 3 , testJob1.getDifficulty());
    }
    
  
    @Test
    public void testOnJobDateTime() {
        assertEquals("Job Date and Time is different.", new Date(), testJob1.getDate());
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
    
}
