/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import exceptions.ExceedsMaxVolunteersException;
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
 * Persistent data initialization.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public final class PresentationSetupRunOnce {

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
	private static void initParks() {
		printGreen("Initializing parks...");
		for (int i = 0; i < PARK_NAME.length; i++) {
			print("Adding park: " + PARK_NAME[i]);
			PARKS[i] = new Park(PARK_NAME[i],  PARK_ADDRESS[i]);
			PARK_CONTROLLER.addPark(PARKS[i]);
		}
		
	}

	// Park Manager
	private static final String[] PARK_MANAGER_NAME = {"andy", "robert"};
	private static final ParkManager[] PARK_MANAGER = new ParkManager[PARK_MANAGER_NAME.length];
	private static void initParkManagers() {
		printGreen("Initializing park managers...");
		for (int i = 0; i < PARK_MANAGER.length; i++) {
			PARK_MANAGER[i] = new ParkManager(PARK_MANAGER_NAME[i]);
			print("Adding Park Manager: " + PARK_MANAGER_NAME[i]);
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
	private static void initVolunteers() {
		printGreen("Initializing volunteers...");
		for (int i = 0; i < VOLUNTEERS.length; i++) {
			final String userName = VOLUNTEERS[i].getUserName();
			print("Adding volunteer: " + userName);
			USER_CONTROLLER.addUser(VOLUNTEERS[i]);
		}
	}
	
	private static final JDialog dialog = new JDialog();
	private static final JPanel panel = new JPanel();

	public static void main(final String... args) {
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		dialog.add(new JScrollPane(panel));
		final JPanel buttonPanel = new JPanel();
		final JButton runButton = new JButton("Run");
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (runButton.getText().equals("Run")) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							Driver.main(null);
						}
					}).start();
					runButton.setEnabled(false);
				}
			}
		});
		final JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.removeAll();
				print("Clearing persistent data.");
				Driver.clearAll();
				dialog.pack();
			}
		});
		final JButton initButton = new JButton("Initialize");
		initButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				initParks();
				initParkManagers();
				initVolunteers();
				dialog.pack();
			}
		});
		final JButton initPersistentData = new JButton("Max Volunteers & Max Jobs Per Day");
		initPersistentData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addJobWithMaxVolunteers();
				addMaxJobsPerDay();
				dialog.pack();
			}
		});
		final JButton maxPendingButton = new JButton("Max Pending Jobs");
		maxPendingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				initMaxPendingJobs();
				dialog.pack();
			}
		});
		
		buttonPanel.add(initButton);
		buttonPanel.add(initPersistentData);
		buttonPanel.add(maxPendingButton);
		buttonPanel.add(runButton);
		buttonPanel.add(clearButton);
		
		
		dialog.add(buttonPanel, BorderLayout.SOUTH);
		panel.setBackground(Color.white);

		dialog.pack();
		dialog.setVisible(true);
	}
	
	private static void print(final String theString) {
		panel.add(new JLabel(theString));
	}
	
	private static void printGreen(final String theString) {
		print("<html><span style=\"color:green;\">" + theString + "</span></html>");
	}
	
	private static void initMaxPendingJobs() {
		final String[] jobNames = {
				"Rodent extermination",
				"Parking facilitation",
				"Debris cleanup",
				"Event security",
				"Watering foliage",
				"Landscaping assistance",
				"Lawn mowing",
				"Poo-poo bag dispenser refilling",
				"Janitorial work",
				"Apple picking",
				"Clearing trail",
				"Sanitizing benches",
				"Relocating the homeless",
				"Painting parking lines",
				"Spreading gravel",
				"Community outreach event staffing",
				"Cleaning barbeque grills",
				"Cleaning picnic tables",
				"Trimming bushes",
				"Parking lot security",
				"Batchroom stall cleaning"
		};
		try {
			printGreen("Initializing maximum pending jobs("+JobController.DEFAULT_MAX_PENDING_JOBS+")...");
			for (int i = 0; i < JobController.DEFAULT_MAX_PENDING_JOBS; i++) {
				print("Adding job: " + jobNames[i]);
				final Job litter = new Job(PARK_MANAGER[0],
						jobNames[i],
						PARKS[0],
						new JobDateTime().addDays(JobController.MIN_FUTURE_DATE_DAYS_FROM_NOW_FOR_JOB_SIGNUP + i),
						new JobDateTime().addDays(JobController.MIN_FUTURE_DATE_DAYS_FROM_NOW_FOR_JOB_SIGNUP + i),
						new JobDateTime().setFromTimeString("10:00 am"),
						new JobDateTime().setFromTimeString("2:00 pm"),
						"Email park manager for more details...",
						Math.floorDiv(Job.MAX_VOLUNTEERS, 3),
						Math.floorDiv(Job.MAX_VOLUNTEERS, 3),
						Math.floorDiv(Job.MAX_VOLUNTEERS, 3));
				JOB_CONTROLLER.addJob(litter);
			}
			
		} catch (ParseException e) { System.out.println("FAIL"); } catch (ExceedsMaxVolunteersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void addMaxJobsPerDay() {
		final String[] jobNames = {
				"Clearing trail",
				"Trash cleanup",
				"Spreading gravel",
				"Janitorial work",
				"Repairing fences",
				"Searching for BigFoot"
		};
		int jobStartDateOffset = JobController.MIN_FUTURE_DATE_DAYS_FROM_NOW_FOR_JOB_SIGNUP
								+ JobController.MAX_JOB_DURATION_DAYS + 1;
		int jobEndDateOffset = jobStartDateOffset;
		final JobDateTime jobStartDate = new JobDateTime().addDays(jobStartDateOffset);
		final JobDateTime jobEndDate = new JobDateTime().addDays(jobEndDateOffset);
		final String jobStartTimeString = "10:00 am";
		final String jobEndTimeString = "11:00 am";
		try {
			printGreen("Adding maximum jobs per day to " + jobStartDate.toDateString() + ".");
			for (int i = 0; i < JobController.MAX_JOBS_PER_DAY; i++) {
				final String jobName = jobNames[i];
				final Job litter = new Job(PARK_MANAGER[0],
						jobName,
						PARKS[0],
						jobStartDate,
						jobEndDate,
						new JobDateTime().setFromTimeString(jobStartTimeString),
						new JobDateTime().setFromTimeString(jobEndTimeString),
						"Call park manager for more details.",
						Math.floorDiv(Job.MAX_VOLUNTEERS, 3),
						Math.floorDiv(Job.MAX_VOLUNTEERS, 3),
						Math.floorDiv(Job.MAX_VOLUNTEERS, 3));
				print("Adding job \"" + jobName + "\" [" + jobStartDate.toDateString() + ", " + jobEndDate.toDateString() + "].");
				JOB_CONTROLLER.addJob(litter);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private static void addJobWithMaxVolunteers() {
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
			printGreen("Adding job \"" + jobName + "\" [" + jobStartDate.toDateString() + ", " + jobEndDate.toDateString() + "] with maximum number of volunteers (" + Job.MAX_VOLUNTEERS + ").");
			for (int i = 0; i < Job.MAX_VOLUNTEERS; i++) {
				job.addVolunteer(VOLUNTEERS[i], WorkDuty.LIGHT);
				VOLUNTEERS[i].addJob(job);
			}
			JOB_CONTROLLER.addJob(job);
		} catch(final ParseException e) {
			e.printStackTrace();
		}
	}
}
