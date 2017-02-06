/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */

package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


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
    
    /**
     * The volunteers for this job.
     */
    private List<String> volunteers;

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
        
        //myDifficulty = theDifficulty;
        
        numOfLightDuty = theLightDuty;
        
        numOfMediumDuty = theMediumDuty;
        
        numOfHeavyDuty = theHeavyDuty;
        
        volunteers = new ArrayList<String>();
        
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
     */
    public void addVolunteer(String theVolunteer)
    {
        volunteers.add(theVolunteer);
    }

    /**
     * Gets the list of volunteers for this job.
     * @return returns the list of volunteers for this job.
     */
    public List<String> getVolunteer()
    {
        return volunteers;
    }
    

    @Override
    public String getKey()
    {
        return myName;
    }
    
    
}
