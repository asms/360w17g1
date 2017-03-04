package presentation;

import java.text.ParseException;

import exceptions.ExceedsMaxVolunteersException;
import model.Job;
import model.JobController;
import model.JobDateTime;
import model.Park;
import model.ParkController;
import model.ParkManager;
import model.UserController;
import model.Volunteer;
import model.Job.WorkDuty;

public class MaxPendingJobsRunOnce {
	public static void main(final String... args) {
		/* START SETUP */
		final UserController uc = new UserController();
		final ParkController pc = new ParkController();
		final JobController jc = new JobController();
		
		final Park sunset = new Park("Sunset", "1124 Sunset Drive W, University Place, WA 98466");
		final Park chambers = new Park("Chambers", "1111 Chambers Drive, University Place, WA 98466");
		
		final ParkManager manager = new ParkManager("robertl");
		manager.associateWithPark(sunset);
		manager.associateWithPark(chambers);
		
		final Volunteer volunteer = new Volunteer("carlyo", "Carly", "O'Hara", "253-425-2375", "carlyo@yahoo.com");
		
		
		try {
			for (int i = 0; i < JobController.DEFAULT_MAX_PENDING_JOBS; i++) {
				final Job litter = new Job(manager, "Cleaning up litter" + i, sunset, new JobDateTime().addDays(4 + i), new JobDateTime().addDays(4 + i), new JobDateTime().setFromTimeString("10:00 am"),
						new JobDateTime().setFromTimeString("2:00 pm"), "Cleaning up after yesterday's little league tournament.", 2, 2,
						2);
				litter.addVolunteer(volunteer, WorkDuty.LIGHT);
				jc.addJob(litter);
			}
			
//			for (int i = 0; i < 4; i++) {
//				final Job litter = new Job(manager, "Cleaning up stuff" + i, sunset, new JobDateTime().addDays(3), new JobDateTime().addDays(3), new JobDateTime().setFromTimeString("10:00 am"),
//						new JobDateTime().setFromTimeString("2:00 pm"), "Cleaning up after yesterday's little league tournament.", 2, 2,
//						2);
//				litter.addVolunteer(volunteer, WorkDuty.LIGHT);
//				jc.addJob(litter);
//			}
			
		} catch (ParseException e) { System.out.println("FAIL"); } catch (ExceedsMaxVolunteersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		uc.addUser(manager);
		uc.addUser(volunteer);
		pc.addPark(sunset);
		pc.addPark(chambers);
		/* END SETUP */
	}
}
