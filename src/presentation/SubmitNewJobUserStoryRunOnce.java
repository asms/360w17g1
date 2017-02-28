/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package presentation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.JobController;
import model.JobDateTime;
import model.ParkController;
import model.UserController;
import model.Job;
import model.Park;
import model.ParkManager;
import model.Volunteer;
import model.Job.WorkDuty;
import ui.Driver;

/**
 * Entry point of the program.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public final class SubmitNewJobUserStoryRunOnce {

	public static void main(final String... args) {
		/* START SETUP */
		final UserController uc = new UserController();
		final JobController jc = new JobController();
		final ParkController pc = new ParkController();
		jc.setMaximumNumberOfPendingJobs(3);
		
		final Park sunset = new Park("Sunset", "1124 Sunset Drive W, University Place, WA 98466");
		final ParkManager manager = new ParkManager("andy");
		manager.associateWithPark(sunset);
		
		final int FOUR_DAYS = 86400 * 4 * 1000;
		
		DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		final Volunteer jackson = new Volunteer("jackson", "Jackson", "Howard", "360-641-6734", "jackson@outlook.com");
		
		final Volunteer[] volunteers = {
				new Volunteer("jake", 	"Jake", 	"Smith",	"253-111-1234", "jake@uw.edu"),
				new Volunteer("wendy", 	"Wendy",	"Anderson", "253-112-7541", "wendy@uw.edu"),
				new Volunteer("sara", 	"Sara", 	"Anderson", "253-113-1271", "sara@uw.edu"),
				new Volunteer("alex", 	"Alex", 	"Anderson", "253-114-7824", "alex@uw.edu"),
				new Volunteer("paul", 	"Paul", 	"Anderson", "253-115-4285", "paul@gmail.com"),
				new Volunteer("ethan", 	"Ethan",	"Anderson", "253-116-1365", "ethan@gmail.com"),
				new Volunteer("monica", "Monica",	"Anderson", "253-117-3466", "asdf@yahoo.com"),
				new Volunteer("joey", 	"Joey", 	"Anderson", "253-118-6543", "space@yahoo.com"),
				new Volunteer("edward",	"Edward",	"Johnson", 	"253-119-7654", "word@yahoo.com"),
				new Volunteer("alan", 	"Alan", 	"Johnson", 	"253-110-4567", "name@yahoo.com"),
				new Volunteer("betty", 	"Betty", 	"Johnson", 	"253-121-1865", "betty@yahoo.com"),
				new Volunteer("bertha",	"Bertha", 	"Johnson", 	"253-131-1451", "bertha@yahoo.com"),
				new Volunteer("mandy", 	"Mandy", 	"Johnson", 	"253-141-1451", "moore@yahoo.com"),
				new Volunteer("roger", 	"Roger", 	"Johnson", 	"253-151-7541", "rabbit@yahoo.com"),
				new Volunteer("paulina","Paulina",	"Johnson", 	"253-161-2457", "paulina@yahoo.com"),
				new Volunteer("jose", 	"Jose", 	"Johnson", 	"253-171-7585", "jose@yahoo.com"),
				new Volunteer("nina", 	"Nina", 	"Johnson", 	"253-181-5368", "nina@yahoo.com"),
				new Volunteer("joe", 	"Joe", 		"Smith", 	"253-191-4311", "smith@yahoo.com"),
				new Volunteer("joseph", "Joseph", 	"Smith", 	"253-176-3411", "jojo@yahoo.com"),
				new Volunteer("rambo", 	"Rambo", 	"Smith", 	"253-121-1451", "rambo@yahoo.com"),
				new Volunteer("et", 	"E.", 		"T.", 		"253-131-1341", "alien@yahoo.com"),
				new Volunteer("nicky", 	"Nicky", 	"Ross",		"253-651-1111", "nicky@yahoo.com"),
				new Volunteer("nick", 	"Nick", 	"Ross",		"253-341-1411", "nick@yahoo.com"),
				new Volunteer("dylan", 	"Dylan", 	"Lee",		"253-151-1431", "dylan@yahoo.com"),
				new Volunteer("george", "George",	"Lee",		"253-971-1111", "george@yahoo.com"),
				new Volunteer("seth", 	"Seth",		"Lee", 		"253-181-1311", "seth@yahoo.com"),
				new Volunteer("carly", 	"Carly",	"Lee",		"253-111-1611", "carly@yahoo.com"),
				new Volunteer("sam", 	"Sam",		"Lee", 		"253-761-1711", "lee@yahoo.com"),
				new Volunteer("mantha",	"Samantha",	"Lee", 		"253-451-1811", "samantha@yahoo.com"),
				new Volunteer("steph", 	"Steph", 	"Lee", 		"253-111-1111", "steph@yahoo.com")
		};
		
		try {
			final Job litter = new Job(manager, "Cleaning up litter", sunset, new JobDateTime().addDays(FOUR_DAYS), new JobDateTime().addDays(FOUR_DAYS + FOUR_DAYS), new JobDateTime().setFromTimeString("10:00 am"),
					new JobDateTime().setFromTimeString("2:00 pm"), "Cleaning up after yesterday's little league tournament.", 30, 0,
					0);
			for (final Volunteer volunteer : volunteers) {
				litter.addVolunteer(volunteer, WorkDuty.LIGHT);
				volunteer.addJob(litter);
			}
			jc.addJob(litter);
			
		} catch (ParseException e) { System.out.println("FAIL"); }
		
		uc.addUser(jackson);
		uc.addUser(manager);
		pc.addPark(sunset);
		/* END SETUP */
		
		Driver.main(null);
		Driver.clearAll();
	}
}
