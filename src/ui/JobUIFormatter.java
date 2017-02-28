package ui;

import model.Job;

public class JobUIFormatter {
	
	/**
	 * String format for toString method.		
	 */		
	private static final String JOB_DISPLAY_FORMAT = ""		
			+ "Name:        %s"				+ System.lineSeparator()		
			+ "Park:        %s"				+ System.lineSeparator()		
			+ "Start Date:  %s"				+ System.lineSeparator()
			+ "End Date:    %s"				+ System.lineSeparator()
			+ "Start Time:  %s" 			+ System.lineSeparator()		
			+ "End Time:    %s"				+ System.lineSeparator()		
			+ "Description: %s" 			+ System.lineSeparator()		
			+ "Number of Volunteers: %s/%s" + System.lineSeparator();

	public static String format(final Job theJob) {
		return (String.format(JOB_DISPLAY_FORMAT,
				theJob.getJobName(),		
				theJob.getPark().getName(),		
				theJob.getStartDate().toDateString(),	
				theJob.getEndDate().toDateString(),
				theJob.getStartTime().toTimeString(),		
				theJob.getEndTime().toTimeString(),		
				theJob.getDescription(),		
				theJob.getVolunteers().size(),
				theJob.getNumberOfPossibleVolunteers()));
	}

}
