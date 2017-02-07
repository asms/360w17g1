/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */

package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * Job model class.
 * @author Mohamed Mohamed
 * @version 1.0
 */
public class Job implements Serializable, UniqueObject
{
    

    
    /**
     * Generated serial version id.
     */
    private static final long serialVersionUID = -4115864921352899827L;


    /**
     * The name of the job.
     */
    private String myName;
    
    
    /**
     * The name of the park job is associated with
     */
    private String myParkName;
    
    /**
     * The date and time of the job.
     */
    private String myDateTime;
    
    /**
     * The description of the job.
     */
    private String myDescription;
    
    /**
     * The level of difficulty of the job.
     */
    private int myDifficulty;
    
    /** Maps of volunteer's email, and work categories. */
    private Map<String, WorkDuty> volunteers;

    /**
     * The number of light duty volunteers.
     */
    private int numOfLightDuty;

    /**
     * The number of medium duty volunteers.
     */
    private int numOfMediumDuty;

    /**
     * The number of heavy duty volunteers.
     */
    private int numOfHeavyDuty;
    
    /**
     * Creates a job object.
     * 
     * @param theName the name of the job.
     * @param thePark the name of the park this job is associated with.
     * @param theLocation the location of the job.
     * @param theDate the date of the job.
     * @param theTime the time of the job.
     * @param theDescription the description of the job.
     * @param theDifficulty the difficulty of the job.
     */
    public Job (final String theName, final String thePark, final String theDateTime,
                    final String theDescription, final int theLightDuty, final int theMediumDuty, final int theHeavyDuty)
    {
        myName = Objects.requireNonNull(theName);
        
        myParkName = Objects.requireNonNull(thePark);
        
        myDateTime = Objects.requireNonNull(theDateTime);
        
        
        myDescription = Objects.requireNonNull(theDescription);
        
        numOfLightDuty = theLightDuty;
        
        numOfMediumDuty = theMediumDuty;
        
        numOfHeavyDuty = theHeavyDuty;
        
        volunteers =  new HashMap<String, Job.WorkDuty>();
        
    }
    
    /**
     * A copy constructor that creates a copy of the existing job.
     * @param job job to be cloned.
     */
    public Job(Job job) {
        this(job.getJobName(), job.getParkName(), job.getDateTime(),
        		job.getDescription(), job.numOfLightDuty, job.numOfMediumDuty, job.numOfHeavyDuty);
    }
    
    /**
     * Returns the title of the job.
     * @return returns the title of the job.
     */
    public String getJobName()
    {
        return myName;
    }
    
    /**
     * Returns the park name job is associated with.
     * @return returns the park name job is associated with.
     */
    public String getParkName()
    {
        return myParkName;
    }
    

    

    
    /**
     * Returns the description of the job.
     * @return returns the description of the job.
     */
    public String getDescription()
    {
        return myDescription;
    }
    
    /**
     * Returns the difficulty of the job. 
     * @return returns the difficulty of the job.
     */
    public int getDifficulty()
    {
        return myDifficulty;
    }
    
    /**
     * Sets the job name.
     */
    public void setJobName(String theName)
    {
        myName = theName;
    }
    
    
    /**
     * Sets the date and time of the job.
     */
    public void setDateTime(String theDateTime)
    {
        myDateTime = theDateTime;
    }
    

    
    /**
     * Sets the description of the job.
     */
    public void setDescription(String theDescription)
    {
        myDescription = theDescription;
    }
    
    /**
     * Sets the difficulty of the job.
     */
    public void setDifficulty(int theDifficulty)
    {
        myDifficulty = theDifficulty;
    }

    /**
     * Returns the date and time of the job.
     * @return returns the date and time of the job.
     */
    public String getDateTime()
    {

        return myDateTime;
    }
    
    /**
     * Adds a volunteer to the list of volunteers for this job.
     * @param theVolunteer the volunteer to be added.
     * @return 
     */
    public boolean addVolunteer(String theVolunteer, WorkDuty workDuty)   {
    	switch(workDuty) {
    		case LIGHT:
    			volunteers.put(theVolunteer, WorkDuty.LIGHT);
    		case MEDIUM:
    			volunteers.put(theVolunteer, WorkDuty.MEDIUM);
    		case HEAVY:
    			volunteers.put(theVolunteer, WorkDuty.HEAVY);
    	}
    	return false;
    }

    /**
     * Gets the list of volunteers for this job.
     * @return returns the list of volunteers for this job.
     */
    public Set<String> getVolunteer()
    {
        return volunteers.keySet();
    }
    
    /**
     * List of volunteers sign up for a job.
     * @return A string containing the list of all the volunteers in this job
     */
    public String getVolunteerString() {
        if (volunteers.isEmpty()) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        String light = "";
        String medium = "";
        String heavy = "";

        for (String vol : volunteers.keySet()) {
            if (volunteers.get(vol) == WorkDuty.LIGHT)
                light += vol + ",";
            else if (volunteers.get(vol) == WorkDuty.MEDIUM)
                medium += vol + ",";
            else
                heavy += vol + ",";
        }
        sb.append(light);
        sb.append(medium);
        sb.append(heavy);
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
    
    @Override
    public boolean equals(final Object theObject) 
    {
        return (theObject instanceof Job)
                && (myName.equals(((Job) theObject).myName))
                && (myParkName.equals(((Job) theObject).myParkName));
    }
    

    @Override
    public String getKey()
    {
        return myName;
    }
    
    /**
     * This represents different work categories that can be used.
     */
    public enum WorkDuty {
        LIGHT, MEDIUM, HEAVY;
    }
    
}
