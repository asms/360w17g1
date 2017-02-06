/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */

package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Job;
import model.Park;
import model.User;
import model.JobController;
import model.ParkController;
import model.UserController;


public class ParkManager {
	
	private static Scanner keyboard;
	
	private static JobController jobController;

	/**
     * This method is for park manager submits a job.
     */
    public static void submitJob() {
        
        jobController = new JobController();
        System.out.println();
        System.out.println("Submit a new job:");
        System.out.println("==================");
        System.out.println();
        
        keyboard = new Scanner(System.in);
        
        //Enter job name 
        System.out.print("Enter Job Name: ");
        String jobName = keyboard.nextLine();
        
        //Enter job location 
        System.out.println("Enter Job Location: ");
        int selection = 1;
        for (String parkName : ParkController.getParks()) {    //call getPark() method in ParkController class so user can input location
            System.out.println(selection++ + ") " + parkName);
        }
        
        int parkSelection = keyboard.nextInt() - 1;
        keyboard.nextLine(); // Skip a line
        String parkName = ParkController.getParks().get(parkSelection); //get that park after selection 
        
        //Enter job date-time
        SimpleDateFormat dayFormat = new SimpleDateFormat("Enter Job Date-Time(MM/dd/yyyy HH:mm): ");
        String dayTime = dayFormat.format(new Date());
        
        //Enter job description
        System.out.print("Enter Job Description: ");
        String jobDescription = keyboard.nextLine();
        
        //Enter # of light-duty volunteers needed
        System.out.print("Enter number of light-duty volunteers needed: ");
        int lightDuty = keyboard.nextInt();
        
        
        //Enter # of medium-duty volunteers needed
        System.out.print("Enter number of medium-duty volunteers needed: ");
        int mediumDuty = keyboard.nextInt();
        
        //Enter # of heavy-duty volunteers needed
        System.out.print("Enter number of heavy-duty volunteers needed: ");
        int heavyDuty = keyboard.nextInt();
        
        System.out.println();
        System.out.println("***********************************************************");
        System.out.println("Title:                       " + jobName);
        System.out.println("Location:                    " + parkName);
        System.out.println("Date-Time:                   " + dayTime);
        System.out.println("Description:                 " + jobDescription);
        System.out.println("# of light-duty volunteers:  " + lightDuty);
        System.out.println("# of medium-duty volunteers: " + mediumDuty);
        System.out.println("# of heavy-duty volunteers:  " + heavyDuty);
        System.out.println("***********************************************************");
   
        System.out.print("Are you sure you want to submit this job (Y/N): ");
        String answer = keyboard.nextLine();
        
        if (answer.equals("Y")) {
            Job j = new Job(jobName, parkName, dayTime, jobDescription, lightDuty, mediumDuty, heavyDuty);
        	jobController.addJob(j); //add job calling JobController method		
        																					  //I assume there is a addJob() method in 
	        System.out.println();
	        System.out.println("***********************************************************");
	        System.out.println("YOU HAVE SUCCESSFULLY ADDED " + jobName + " TO " + parkName + ".");
	        System.out.println("***********************************************************");
	        System.out.println();
        } else {
        	System.exit(0);
        	
        }
    }
    
    /**
     * This method is to view volunteers sign up for a job that park manager managed.
     */
    public static void viewVolunteers() {

        // Display all the jobs of that park manager managed
        System.out.println("Select a job to view its volunteers:");
        List <Job> currentJobs = jobController.getJobs();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        // create list of Job -> calling Job class and getJob() in JobCOntroller 

        if (currentJobs.isEmpty()) {
            System.out.println("You do not have any jobs -> so no volunteers!");
        }
        
        else {
            int i = 1;
            for (Job j : currentJobs) {
                System.out.print(i++ + ") ");
                System.out.print(j.getJobName() + " at ");
                System.out.print(j.getParkName() + " on ");
                System.out.println(j.getDateTime());
                System.out.println();
            }

            // Ask user to choose from the list
            //keyboard = new Scanner(System.in);
            System.out.print("Enter a number from the list: ");
            int choice = keyboard.nextInt();
            while (choice < 0 || choice > currentJobs.size()) {
                System.out.print("Please make a selection from the list: ");
                choice = keyboard.nextInt();
            }

            // Display volunteers of the selected jobs:
            System.out.println();
            System.out.println("Volunteers:");

            
            boolean volunteerFound = false;
            for (String volunteer : currentJobs.get(choice - 1)) {
                for (User u : UserController.getUserList()) {   //get user from UserController
                    if (u.equals("volunteer")) {
                        volunteerFound = true;
                        System.out.print(u.getFirstName() + " ");
                        System.out.print(u.getLastName() + ", ");
                    }
                }
            }
            if (!volunteerFound) {
                System.out.println("No volunteers have signed up yet!");
            }
        }
        System.out.println(); 
    }
}
