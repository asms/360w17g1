package model;

import java.util.List;
import java.util.Scanner;

import model.Job;
import model.Park;
import model.User;
import model.JobController;
import model.ParkController;
import model.UserController;

public class Volunteer {
	
	private static Scanner keyboard;
    public static User volunteer;
    
	/**
     * This method is views list of upcoming jobs 
     * so volunteer can look at and sign up for. 
     * @return list of upcoming jobs 
     */
    public static List<Job> viewUpcomingJobs() { 

        System.out.println("Available upcoming jobs:");
        System.out.println();

        final List<Job> upcomingJobs = JobController.getUpcomingJobs(); //calling Job class and getUpComingJobs() from JobController class 

        int i = 1;
        for (Job j : upcomingJobs) {
            System.out.print(i++ + ") ");
            System.out.println(j.getJobName() + " at\t");
            System.out.println(j.getParkName() + " on\t");
            System.out.println(j.getDateTime());
            System.out.println();
        }

        if (upcomingJobs.isEmpty()) {
            System.out.println("There are no upcoming jobs to display!");
        }

        System.out.println();

        return upcomingJobs;
    }
    
	/**
	 * This method is for volunteer signs up for a job  
	 */
    public static void signUpforJob() {

        System.out.println("Sign up for a job:");
        List<Job> upcomingJobs = viewUpcomingJobs(); //list of upcoming jobs 

        if (!upcomingJobs.isEmpty()) {
            // User selects number:
            System.out.print("Select a number corresponding to the job you are interested in: ");
            keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();

            // Make sure user selects job in valid range (prevent index oob)
            while (choice < 0 || choice > upcomingJobs.size()) {
                System.out.print("Please make a selection from the list: ");
                choice = keyboard.nextInt();
            }

            System.out.print("Please select a work category choice 1(light), 2(medium), 3(heavy): ");
            System.out.println();
            int workDuty = keyboard.nextInt();
            System.out.println();

            String jobName = upcomingJobs.get(choice - 1).getJobName();
            String parkName = upcomingJobs.get(choice - 1).getParkName();

            boolean addedVolunteer = false;
            
            
            //those methods below are basically I assume will include in Job class. 
            switch (workDuty) {
	            case 1:
	            	addedVolunteer = upcomingJobs.get(choice - 1).addVolunteer(volunteer.getVolunteer(),workDuty.LIGHT);
	            	break;
	            case 2:
	            	addedVolunteer = upcomingJobs.get(choice - 1).addVolunteer(volunteer.getVolunteer(),workDuty.MEDIUM);
	            	break;
	            case 3:
	            	addedVolunteer = upcomingJobs.get(choice - 1).addVolunteer(volunteer.getVolunteer(),workDuty.HEAVY);	
	            	break;
	            default:
	            	System.out.println("Please enter a valid work category");
	            	System.out.println("Light, Medium, Heavy");
	            	workDuty = keyboard.nextInt();
            }

            if (addedVolunteer) {
            	System.out.println("*************************************************************");
            	System.out.println("SUCCESSFULLY SIGNED UP FOR " + jobName
            			+ " at " + parkName + ".");
            	System.out.println("*************************************************************");
            }
            
            else {
            	System.out.println("SORRY! UNABLE TO SIGN UP FOR THIS JOB");
            }

            System.out.println();
        }

    }
    
    /**
     * This method is for volunteer views list of signed up job. 
     */
    public static void viewSignedUpJobs() {

        System.out.println("Viewing the jobs you have signed up for:");
        System.out.println();
        
        boolean nameFound = true;
        for (Job j : JobController.getUpcomingJobs()) {

            for (String volunteer : j.getVolunteer()) {
                if (volunteer.getName().equals(volunteer)) { //getName() from User
                    nameFound = true;
                    System.out.print(j.getJobName() + " at ");
                    System.out.print(j.getParkName() + " on ");
                    System.out.println(j.getDateTime());
                    System.out.println();
                }
            }
        }

        // The case when volunteer has no jobs:
        if (!nameFound) {
            Scanner console = new Scanner(System.in);
            System.out.println("You have not signed up for any jobs yet!");
            System.out.println("Do you want to sign up a job? Y/N: ");
            String answer = console.next();
            if (answer.equalsIgnoreCase("Y")) {
            	signUpforJob();
            }
            System.out.println();
        }
    }
}
	
