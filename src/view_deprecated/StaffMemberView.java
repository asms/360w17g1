package view_deprecated;

import java.util.List;

import controller.JobController;
import model.Job;
import model.StaffMember;
import model.AbstractUser;

public class StaffMemberView {

	public static AbstractUser staffMember;
	private static JobController jobController = new JobController();

	/**
	 * This method is views list of upcoming jobs so volunteer can look at and
	 * sign up for.
	 * 
	 * @return list of upcoming jobs
	 */
	public static List<Job> viewUpcomingJobs() {

		System.out.println("Available upcoming jobs:");
		System.out.println();

		final List<Job> upcomingJobs = jobController.getUpcomingJobs(); 

		int i = 1;
		for (Job j : upcomingJobs) {
			System.out.print(i++ + ") ");
			System.out.println(j.getJobName() + " at\t");
			System.out.println(j.getPark() + " on\t");
			System.out.println(j.getDate());
			System.out.println();
		}

		if (upcomingJobs.isEmpty()) {
			System.out.println("There are no upcoming jobs to display!");
		}

		System.out.println();

		return upcomingJobs;
	}

	public static List<Job> viewPastJobs() {

		System.out.println("View past jobs:");
		System.out.println();

		final List<Job> previousJobs = ((StaffMember) staffMember).getPastJobs(); 

		int i = 1;
		for (Job j : previousJobs) {
			System.out.print(i++ + ") ");
			System.out.println(j.getJobName() + " at\t");
			System.out.println(j.getPark() + " on\t");
			System.out.println(j.getDate());
			System.out.println();
		}

		if (previousJobs.isEmpty()) {
			System.out.println("There are no upcoming jobs to display!");
		}

		System.out.println();

		return previousJobs;

	}
}
