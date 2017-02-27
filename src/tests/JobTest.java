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
import model.Volunteer;

public class JobTest {
    
	
    private Job jobToUseForTests;
    
    /** This represents volunteers signed up for light work category. */
    private Map<String, WorkDuty> volunteerLight;
    
    /** This represents volunteers signed up for medium work category. */
    private Map<String, WorkDuty> volunteerMed;
    
    /** This represents volunteers signed up for heavy work category. */
    private Map<String, WorkDuty> volunteerHeavy;
    
    /**
     * Initialize the objects.
     *
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        
    	
    	final JobDate date = new JobDate();
        jobToUseForTests = new Job(new ParkManager("pm"), "Park Cleanup", new Park("Cherry Park", "Park Location"),  date, date, date, "Cleaning up litter", 30, 1, 0);
        
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
    public void Equals_EqualSameJobName_ExpectedTrueReturn() {
        final Job differentJobName = new Job(new ParkManager("pm"), "Park Cleanup", new Park("Cherry Park", "Park Location"),  new JobDate(), new JobDate(), new JobDate(), "Cleaning up litter", 1, 3, 5);
        assertTrue(differentJobName.getJobName().equals(jobToUseForTests.getJobName()));
    }
    
    @Test
    public void Equals_NotEqualDifferentJobName_ExpectedFalseReturn() {
    	final Job differentJobName = new Job(new ParkManager("pm"), "Different Job Name", new Park("Cherry Park", "Park Location"),  new JobDate(), new JobDate(), new JobDate(), "Cleaning up litter", 1, 3, 5);
        assertFalse(differentJobName.getJobName().equals(jobToUseForTests.getJobName()));
    }
    
    @Test
    public void WorkDutyEnum_VolunteerSignedUpForLight_ExpectedTrue() {
        assertEquals("Volunteer who signed up for Light",
                     volunteerLight.get("volunteerLight"), WorkDuty.LIGHT);
    }
    
    @Test
    public void WorkDutyEnum_VolunteerSignedUpForMedium_ExpectedTrue() {
        assertEquals("Volunteer who signed up for Medium",
                     volunteerMed.get("volunteerMedium"), WorkDuty.MEDIUM);
    }
    
    @Test
    public void WorkDutyEnum_VolunteerSignedUpForHeavy_ExpectedTrue() {
        assertEquals("Volunteer who signed up for Heavy",
                     volunteerHeavy.get("volunteerHeavy"), WorkDuty.HEAVY);
    }
    
    @Test (expected = NullPointerException.class)
    public void addVolunteer_NullWorkDuty(){
        final Volunteer eli = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
        jobToUseForTests.addVolunteer(eli, null);

    }
    
    @Test
    public void addVolunteer_MaxVolunteersHasBeenExceeded_ExpectedTrue(){
        final Volunteer eli = new Volunteer("eli", "Eli", "Ile", "253-123-4567", "eli@gmail.com");
        assertTrue(jobToUseForTests.addVolunteer(eli, WorkDuty.LIGHT));
        
    }
    
    @Test
    public void addVolunteer_MaxVolunteersHasNotBeenExceeded_ExpectedFalse(){
        
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
            jobToUseForTests.addVolunteer(volunteers[i], WorkDuty.LIGHT);
        }

        assertFalse(jobToUseForTests.addVolunteer(jackson, WorkDuty.LIGHT));
    }

    
    @Test (expected = NullPointerException.class)
    public void NeedsWorkDuty_NullWorkDuty(){
        jobToUseForTests.needsWorkDuty(null);
    }
    
    @Test
    public void NeedsWorkDuty_jobDoesNeedWorkDutyOfThisType_expectedTrue(){
        assertTrue(jobToUseForTests.needsWorkDuty(WorkDuty.LIGHT));
    }
    
    @Test
    public void NeedsWorkDuty_jobDoesNotNeedWorkDutyOfThisType_expectedFalse(){
        final Volunteer jackson = new Volunteer("jackson", "Jackson", "Howard", "360-641-6734", "jackson@outlook.com");
        jobToUseForTests.addVolunteer(jackson, WorkDuty.MEDIUM);
        assertFalse(jobToUseForTests.needsWorkDuty(WorkDuty.MEDIUM));
    }
}
