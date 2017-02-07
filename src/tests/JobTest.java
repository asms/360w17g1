/**
 * Group 1
 */
package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.Job.WorkDuty;
import model.User;

public class JobTest {
    
    /** This represents different jobs for testing. */
    private Job testJob1, testJob2, testJob3;
    
    /** This represents volunteers sign up for all work categories. */
    private Map<String, WorkDuty> volunteer;
    
    /** This represents volunteers sign up for light work category. */
    private Map<String, WorkDuty> volunteerLight;
    
    /** This represents volunteers sign up for medium work category. */
    private Map<String, WorkDuty> volunteerMed;
    
    /** This represents volunteers sign up for heavy work category. */
    private Map<String, WorkDuty> volunteerHeavy;
    
    /** This represents a user. */
    private User user;
    
    /**
     * Initialize the objects.
     *
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        
        //represent a user (volunteer)
        user = new User("volunteer");
        
        //represent volunteers sign up for light work category
        volunteerLight = new HashMap<String, WorkDuty>();
        volunteerLight.put("volunteerLight", WorkDuty.LIGHT);
        volunteerLight.put("volunteerLight2", WorkDuty.LIGHT);
        
        //represent volunteers sign up for medium work category
        volunteerMed = new HashMap<String, WorkDuty>();
        volunteerMed.put("volunteerMedium", WorkDuty.MEDIUM);
        
        //represent volunteers sign up for heavy work category
        volunteerHeavy = new HashMap<String, WorkDuty>();
        volunteerHeavy.put("volunteerHeavy", WorkDuty.HEAVY);
        
        //represent volunteers sign up for all work category
        volunteer = new HashMap<String, WorkDuty>();
        volunteer.put("volunteerLight", WorkDuty.LIGHT);
        volunteer.put("volunteerMedium", WorkDuty.MEDIUM);
        volunteer.put("volunteerHeavy", WorkDuty.HEAVY);
    }
    
    @Test
    public void testOnParkName() {
        assertEquals("Park name is different.", "Cherry Park", testJob1.getParkName());
    }
    
    @Test
    public void testOnJobName() {
        assertEquals("Job name should be the same.", "Park Cleanup", testJob1.getJobName());
    }
    
    @Test
    public void testOnJobDescription() {
        assertEquals("Job Description is different", "Cleaning up litter", testJob1.getDescription());
    }
    
    @Test
    public void testOnJobDifficultity() {
        assertEquals("Job Difficulity should be the same.", 3 , testJob1.getDifficulty());
    }
    
    @Test
    public void testOnJobDateTime() {
        assertEquals("Job Date and Time is different.", "02/25/2017 10:00", testJob1.getDateTime());
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
    public void testContainOnHaveVolunteer() {
        assertTrue("This job doesn't contain a volunteer with that username.", testJob2.contains(user.getUserName()));
    }
    
    @Test
    public void testContainOnNoVolunteer() {
        assertFalse("This job contain a volunteer with that username.", testJob3.contains(user.getUserName()));
    }
    
    
}
