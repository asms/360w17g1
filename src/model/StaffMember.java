package model;

import java.util.ArrayList;
import java.util.List;

public class StaffMember extends AbstractUser {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 3901221276745275829L;
	
	private final List<Job> pastJobs;

	public StaffMember(String theUserName) {
		super(theUserName);
		pastJobs = new ArrayList<Job>();
	}

	public List<Job> getPastJobs() {
		return pastJobs;
	}

	public void addPastJobs(Job thePastJob) {
		pastJobs.add(thePastJob);
	}
	
	public void removePastJobs(Job thePastJob) {
		if (pastJobs.contains(thePastJob)){
			pastJobs.remove(thePastJob);
		}
		else{
			System.out.println("Job was not found: " + thePastJob);
		}
	}
	
}
