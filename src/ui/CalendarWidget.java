package ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Job;

public class CalendarWidget {
	
	private final Calendar myCalendar;
	private final List<Job> myJobs;
	private final DateFormat myDayFormat;
	private final DateFormat myMonthFormat;
	private final DateFormat myDateFormat;
	private final int myMaxPendingJobs;
	
	public CalendarWidget(final List<Job> theUpcomingJobs, final int theMaxPendingJobs) {
		myCalendar = Calendar.getInstance();
		myJobs = theUpcomingJobs;
		myDayFormat = new SimpleDateFormat("DDDD");
		myMonthFormat = new SimpleDateFormat("MMMM");
		myDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		myMaxPendingJobs = theMaxPendingJobs;
	}
	
	public void print() {
		myCalendar.add(Calendar.DATE, 1);
		final String currentMonth = myMonthFormat.format(myCalendar.getTime());
		final String header = getDaysHeader();
		final int width = header.length();
		System.out.println(header);
		System.out.println(center("[" + currentMonth+ "]", width));
		int dayOfWeek = myCalendar.get(Calendar.DAY_OF_WEEK) - 1;
		System.out.print(space(13*dayOfWeek));
		int d;
		for (int i=0; i<myMaxPendingJobs; i++) {
			d = myCalendar.get(Calendar.DAY_OF_WEEK);
			System.out.print( center(String.valueOf(myCalendar.get(Calendar.DATE)) + ":" + String.valueOf(getNumJobs()), 13) );
			myCalendar.add(Calendar.DATE, 1);
			if (d == Calendar.SATURDAY) {
				System.out.println("");
			}
		}
		System.out.println("");
	}
	
	private long getNumJobs() {
		return myJobs.stream().filter(x -> datesEqual(x.getDate(), myCalendar.getTime())).count();
	}
	
	private boolean datesEqual(Date one, Date two) {
		return myDateFormat.format(one).equals(myDateFormat.format(two));
	}
	
	public String getDaysHeader() {
		final StringBuilder sb = new StringBuilder();
		final String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		for (final String day : days) {
			sb.append("|" + center(day, 11) + "|");
		}
		return sb.toString();
	}
	
	public String center(final String theString, final int theWidth) {
		final int padding = (theWidth - theString.length()) / 2;
		return space(padding) + theString + space(theWidth - padding - theString.length());
	}
	
	public String space(final int theSpace) {
		return new String(new char[theSpace]).replace("\0", " ");
	}

}
