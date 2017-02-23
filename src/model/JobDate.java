/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * A convenient Job date class for handling job dates and times.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public class JobDate implements Serializable, Comparable<JobDate> {
	
	/** Generated serial version UID. */
	private static final long serialVersionUID = 7290022203248016337L;
	
	private static final String DATE_FORMAT = "MM/dd/yyyy";
	private static final String TIME_FORMAT = "hh:mm a";
	
	/** The wrapped data structure of this date class. */
	private Date myDate;
	
	private final SimpleDateFormat myDateFormat;
	private final SimpleDateFormat myTimeFormat;
	
	/**
	 * Creates a new job date at the specified date and time.
	 * @param theDate a date
	 * @throws NullPointerException if the date is null
	 */
	public JobDate(final Date theDate) {
		myDate = (Date) Objects.requireNonNull(theDate).clone();
		myDateFormat = new SimpleDateFormat(DATE_FORMAT);
		myTimeFormat= new SimpleDateFormat(TIME_FORMAT);
	}
	
	/**
	 * Creates a new jobs date at the current date and time.
	 */
	public JobDate() {
		this(new Date());
	}
	
	/**
	 * Gets the start of this date as a separate object.
	 * @return the beginning of the this date
	 */
	public JobDate getStartOfDate() {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new JobDate(calendar.getTime());
	}
	
	/**
	 * Sets this date's time to the time parameter.
	 * @param theTime a date object with a specific time
	 * @return a reference to this job date for chaining
	 * @throws NullPointerException if the time is null
	 */
	public JobDate setTime(final Date theTime) {
		final Calendar time = Calendar.getInstance();
		final Calendar out = Calendar.getInstance();
		time.setTime(Objects.requireNonNull(theTime));
		out.set(Calendar.HOUR, time.get(Calendar.HOUR));
		out.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
		out.set(Calendar.SECOND, time.get(Calendar.SECOND));
		out.set(Calendar.MILLISECOND, time.get(Calendar.MILLISECOND));
		myDate = out.getTime();
		return this;
	}
	
	/**
	 * Adds years to this date.
	 * @param theYears the number of years to add
	 * @return a reference to this job date for chaining
	 */
	public JobDate addYears(final int theYears) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.add(Calendar.YEAR, theYears);
		myDate = calendar.getTime();
		return this;
	}
	
	/**
	 * Adds months to this date.
	 * @param theMonths the number of months to add
	 * @return a reference to this job date for chaining
	 */
	public JobDate addMonths(final int theMonths) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.add(Calendar.MONTH, theMonths);
		myDate = calendar.getTime();
		return this;
	}
	
	/**
	 * Adds days to this date.
	 * @param theDays the number of days to add
	 * @return a reference to this job date for chaining
	 */
	public JobDate addDays(final int theDays) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.add(Calendar.DATE, theDays);
		myDate = calendar.getTime();
		return this;
	}
	
	/**
	 * Compares two dates irrespective of time of day.
	 * @param theFirst a date
	 * @param theSecond another date
	 * @return whether the dates are the same
	 * @throws NullPointerException if either dates are null
	 */
	public static boolean sameDates(final JobDate theFirst, final JobDate theSecond) {
		return Objects.requireNonNull(theFirst).getStartOfDate()
				.equals(Objects.requireNonNull(theSecond).getStartOfDate());
	}
	
	/**
	 * Sets the current date from the time string.
	 * @param timeString a string in the format hh:mm am/pm
	 * @return a reference to this job date for chaining
	 * @throws ParseException if the string is not well formed
	 */
	public JobDate setFromTimeString(final String timeString) throws ParseException {
		myDate = myTimeFormat.parse(timeString);
		return this;
	}
	
	/**
	 * Sets the current date from the date string.
	 * @param dateString a string in the format HH/mm/yyyy
	 * @return a reference to this job date for chaining
	 * @throws ParseException if the string is not well formed
	 * @throws NullPointerException if the date string is null
	 */
	public JobDate setFromDateString(final String dateString) throws ParseException {
		myDate = myDateFormat.parse(Objects.requireNonNull(dateString));
		return this;
	}
	
	/**
	 * Checks if this date is between two dates.
	 * @param theMin the minimum date
	 * @param theMax the maximum date
	 * @return true if this date is between two dates
	 * @throws NullPointerException if either date is null
	 */
	public boolean between(final JobDate theMin, final JobDate theMax) {
		return myDate.after(Objects.requireNonNull(theMin).myDate) && myDate.before(Objects.requireNonNull(theMax).myDate);
	}
	
	/**
	 * 
	 * @param theDate a date
	 * @return true if this date is after the date
	 * @throws NullPointerException if the date is null
	 */
	public boolean after(final JobDate theDate) {
		return myDate.after(Objects.requireNonNull(theDate).myDate);
	}
	
	/**
	 * Checks whether this date is before a date.
	 * @param theDate a date
	 * @return true if this date is before the date
	 * @throws NullPointerException if the date is null
	 */
	public boolean before(final JobDate theDate) {
		return myDate.before(Objects.requireNonNull(theDate).myDate);
	}
	
	/**
	 * Outputs this job date as a time string.
	 * @return the time string
	 */
	public String toTimeString() {
		return myTimeFormat.format(myDate);
	}
	
	/**
	 * Outputs this job date as a date string.
	 * @return the date string
	 */
	public String toDateString() {
		return myDateFormat.format(myDate);
	}
	
	@Override
	public boolean equals(final Object theObject) {
		return theObject instanceof JobDate && myDate.equals(((JobDate) theObject).myDate);
	}
	
	@Override
	public int hashCode() {
		return myDate.hashCode();
	}
	
	@Override
	public int compareTo(final JobDate date) {
		return myDate.compareTo(date.myDate);
	}

}
