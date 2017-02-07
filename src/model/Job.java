/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */

package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * Job model class.
 * @author Mohamed Mohamed
 * @version 1.0
 */
public class Job implements Serializable//, UniqueObject
{
    

    
    /**
     * Generated serial version id.
     */
    private static final long serialVersionUID = -4115864921352899827L;
    
    /**
     * String format for toString method.
     */
    private static final String STRING_FORMAT = ""
    		+ "name:        %s" + System.lineSeparator()
    		+ "Park:        %s" + System.lineSeparator()
    		+ "Date:        %s" + System.lineSeparator()
    		+ "Start Time:  %s" + System.lineSeparator()
    		+ "End Time:    %s" + System.lineSeparator()
    		+ "Description: %s" + System.lineSeparator()
    		+ "Volunteers:"       + System.lineSeparator()
    		+ "Light Duty:  %s" + System.lineSeparator()
    		+ "Medium Duty: %s" + System.lineSeparator()
    		+ "Heavy Duty:  %s";


    /**
     * The name of the job.
     */
    private String myName;
    
    
    /**
     * The park the job is associated with
     */
    private Park myPark;
    
    /**
     * The date of the job.
     */
    private Date myDate;
    
    /**
     * The start time of the job.
     */
    private Date myStartTime;
    
    /**
     * The end time of the job.
     */
    private Date myEndTime;
    
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
    public Job (final String theName, final Park thePark, final Date theDate, final Date theStartTime,
    		final Date theEndTime, final String theDescription, final int theLightDuty, final int theMediumDuty,
    		final int theHeavyDuty)
    {
        myName = Objects.requireNonNull(theName);
        
        myPark = Objects.requireNonNull(thePark);
        
        myDate = Objects.requireNonNull(theDate);
        
        myStartTime = Objects.requireNonNull(theStartTime);
        
        myEndTime = Objects.requireNonNull(theEndTime);
        
        
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
    public Park getPark()
    {
        return myPark;
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
     * Sets the date of the job.
     */
    public void setDate(Date theDate)
    {
        myDate = Objects.requireNonNull(theDate);
    }
    
    /**
     * Sets the start time of the job.
     */
    public void setStartTime(Date theStartTime)
    {
        myStartTime = Objects.requireNonNull(theStartTime);
    }
    
    /**
     * Sets the end time of the job.
     */
    public void setEndTime(final Date theEndTime)
    {
        myEndTime = Objects.requireNonNull(theEndTime);
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
     * Returns the date of the job.
     * @return returns the date of the job.
     */
    public Date getDate()
    {
        return myDate;
    }
    
    /**
     * Returns the start time of the job.
     * @return returns the date of the job.
     */
    public Date getStartTime()
    {
        return myStartTime;
    }
    
    /**
     * Returns the end time of the job.
     * @return returns the date of the job.
     */
    public Date getEndTime()
    {
        return myEndTime;
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
    public boolean equals(final Object theObject) 
    {
        return (theObject instanceof Job)
                && (myName.equals(((Job) theObject).myName))
                && (myPark.equals(((Job) theObject).myPark));
    }
    
    @Override
    public String toString() {
    	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
    	return String.format(STRING_FORMAT,
    			myName,
				myPark,
				dateFormat.format(myDate),
				timeFormat.format(myStartTime),
				timeFormat.format(myEndTime),
				myDescription,
				numOfLightDuty,
				numOfMediumDuty,
				numOfHeavyDuty);
    }
    
/*
    @Override
    public String getKey()
    {
        return myName;
    }
  */  
    
}
