/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package presentation;

import java.text.ParseException;

import model.JobController;
import model.JobDateTime;
import model.ParkController;
import model.UserController;
import model.Job;
import model.Park;
import model.ParkManager;
import model.Volunteer;
import model.Job.WorkDuty;

/**
 * Persistent data initialization.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public final class SubmitNewJobUserStoryRunOnce {

	// Controllers
	private static final UserController USER_CONTROLLER = new UserController();
	private static final JobController JOB_CONTROLLER = new JobController();
	private static final ParkController PARK_CONTROLLER = new ParkController();

	// Park
	private static final String[] PARK_NAME = {"Sunset", "Chambers"};
	private static final String[] PARK_ADDRESS = {
			"1124 Sunset Drive W, University Place, WA 98466",
			"1111 Chambers Drive, University Place, WA 98466"
			};
	private static final Park[] PARKS = new Park[PARK_NAME.length];
	static {
		for (int i = 0; i < PARK_NAME.length; i++) {
			System.out.println("Adding park: " + PARK_NAME[i]);
			PARKS[i] = new Park(PARK_NAME[i],  PARK_ADDRESS[i]);
			PARK_CONTROLLER.addPark(PARKS[i]);
		}
		
	}

	// Park Manager
	private static final String[] PARK_MANAGER_NAME = {"andy", "robert"};
	private static final ParkManager[] PARK_MANAGER = new ParkManager[PARK_MANAGER_NAME.length];
	static {
		for (int i = 0; i < PARK_MANAGER.length; i++) {
			PARK_MANAGER[i] = new ParkManager(PARK_MANAGER_NAME[i]);
			System.out.println("Adding Park Manager: " + PARK_MANAGER_NAME[i]);
			USER_CONTROLLER.addUser(PARK_MANAGER[i]);
		}
	}

	// Volunteers
	private static final Volunteer[] VOLUNTEERS = {
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
	static {
		for (int i = 0; i < VOLUNTEERS.length; i++) {
			final String userName = VOLUNTEERS[i].getUserName();
			System.out.println("Adding volunteer: " + userName);
			USER_CONTROLLER.addUser(VOLUNTEERS[i]);
		}
	}

	public static void main(final String... args) {
		String jobName = "Rehabilitation and Relining Culverts";
		String jobDescription = "Install a reinforced concrete invert to repair or replace a deteriorated invert in a "
				+ "corrugated metal culvert pipe, slipline or install a new internal pipe inside the existing culvert, "
				+ "spot patch and repair, repair and modification to culvert end treatment, apply internal bands or "
				+ "similar repairs to problem joints, apply a shotcrete or gunnite lining, apply a shotcrete or "
				+ "gunnite lining, and stabilize the fill surrounding a culvert or fill isolated voids in the backfill "
				+ "envelope.";
		int jobStartDateOffset = JobController.MIN_FUTURE_DATE_DAYS_FROM_NOW_FOR_JOB_SIGNUP;
		int jobEndDateOffset = jobStartDateOffset + JobController.MAX_JOB_DURATION_DAYS;
		final JobDateTime jobStartDate = new JobDateTime().addDays(jobStartDateOffset);
		final JobDateTime jobEndDate = new JobDateTime().addDays(jobEndDateOffset);
		final String jobStartTimeString = "10:00 am";
		final String jobEndTimeString = "11:00 am";
		try {
			final JobDateTime jobStartTime = new JobDateTime().setFromTimeString(jobStartTimeString);
			final JobDateTime jobEndTime = new JobDateTime().setFromTimeString(jobEndTimeString);

			Job job = new Job(
					PARK_MANAGER[0],
					jobName,
					PARKS[0],
					jobStartDate,
					jobEndDate,
					jobStartTime,
					jobEndTime,
					jobDescription,
					Job.MAX_VOLUNTEERS,
					0,
					0);
			System.out.println("Adding maximum number of volunteers (" + Job.MAX_VOLUNTEERS + ") to \"" + jobName + "\".");
			for (int i = 0; i < Job.MAX_VOLUNTEERS; i++) {
				job.addVolunteer(VOLUNTEERS[i], WorkDuty.LIGHT);
				VOLUNTEERS[i].addJob(job);
			}
			JOB_CONTROLLER.addJob(job);
			System.out.println("Adding job: \"" + jobName + "\".");
		} catch(final ParseException e) {
			e.printStackTrace();
		}
	}
}
