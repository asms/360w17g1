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
    
	JobController pk = new JobController();
	
    private Job jobToUseForTests;
    
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
    public void Equals_NotEqualDifferentParkName_ExpectedFalseReturn() {
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
            jobToUseForTests.addVolunteer(volunteers[i], WorkDuty.LIGHT);
        }

        assertFalse(jobToUseForTests.addVolunteer(jackson, WorkDuty.LIGHT));
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
