/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * A convenient Job date class for handling job dates and times.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public class JobDateTime implements Serializable, Comparable<JobDateTime> {

	/** Generated serial version UID. */
	private static final long serialVersionUID = 7290022203248016337L;

	private static final String DATE_FORMAT = "MM/dd/yyyy";
	private static final String TIME_FORMAT = "hh:mm a";

	/** The wrapped data structure of this date class. */
	private LocalDateTime myDate;
	
	private final DateTimeFormatter myDateFormatter;
	private final DateTimeFormatter myTimeFormatter;

	/**
	 * Creates a new job date at the specified date and time.
	 * @param theDate a date
	 * @throws NullPointerException if the date is null
	 */
	public JobDateTime(final LocalDateTime theDate) {
		myDate = Objects.requireNonNull(theDate);
		myDateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		myTimeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
	}

	/**
	 * Creates a new jobs date at the current date and time.
	 */
	public JobDateTime() {
		this(LocalDateTime.now());
	}

	/**
	 * Gets the start of this date as a separate object.
	 * @return the beginning of the this date
	 */
	public JobDateTime getStartOfDate() {
		return new JobDateTime(myDate.with(LocalTime.MIN));
	}

	/**
	 * Sets this date's time to the time parameter.
	 * @param theTime a date object with a specific time
	 * @return a reference to this job date for chaining
	 * @throws NullPointerException if the time is null
	 */
	public JobDateTime setTime(final LocalDateTime theTime) {
		myDate = LocalDateTime.of(myDate.toLocalDate(), theTime.toLocalTime());
		return this;
	}

	/**
	 * Adds years to this date.
	 * @param theYears the number of years to add
	 * @return a reference to this job date for chaining
	 */
	public JobDateTime addYears(final int theYears) {
		myDate = myDate.plusYears(theYears);
		return this;
	}

	/**
	 * Adds months to this date.
	 * @param theMonths the number of months to add
	 * @return a reference to this job date for chaining
	 */
	public JobDateTime addMonths(final int theMonths) {
		myDate = myDate.plusMonths(theMonths);
		return this;
	}

	/**
	 * Adds days to this date.
	 * @param theDays the number of days to add
	 * @return a reference to this job date for chaining
	 */
	public JobDateTime addDays(final int theDays) {
		myDate = myDate.plusDays(theDays);
		return this;
	}

	public JobDateTime addHours(final int theHours) {
		myDate = myDate.plusHours(theHours);
		return this;
	}

	/**
	 * Compares two dates irrespective of time of day.
	 * @param theFirst a date
	 * @param theSecond another date
	 * @return whether the dates are the same
	 * @throws NullPointerException if either dates are null
	 */
	public static boolean sameDates(final JobDateTime theFirst, final JobDateTime theSecond) {
		return Objects.requireNonNull(theFirst).getStartOfDate()
				.equals(Objects.requireNonNull(theSecond).getStartOfDate());
	}

	/**
	 * Sets the current date from the time string.
	 * @param timeString a string in the format hh:mm am/pm
	 * @return a reference to this job date for chaining
	 * @throws ParseException if the string is not well formed
	 */
	public JobDateTime setFromTimeString(final String timeString) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(TIME_FORMAT);
		myDate = LocalDateTime.of(myDate.toLocalDate(), LocalTime.parse(df.format(df.parse(timeString)), myTimeFormatter));
		return this;
	}

	/**
	 * Sets the current date from the date string.
	 * @param dateString a string in the format MM/dd/yyyy
	 * @return a reference to this job date for chaining
	 * @throws ParseException if the string is not well formed
	 * @throws NullPointerException if the date string is null
	 */
	public JobDateTime setFromDateString(final String dateString) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		myDate = LocalDateTime.of(LocalDate.parse(df.format(df.parse(dateString)), myDateFormatter), myDate.toLocalTime());
		return this;
	}

	/**
	 * Checks if this date is between two dates.
	 * @param theMin the minimum date
	 * @param theMax the maximum date
	 * @return true if this date is between two dates
	 * @throws NullPointerException if either date is null
	 */
	public boolean between(final JobDateTime theMin, final JobDateTime theMax) {
		return myDate.isAfter(Objects.requireNonNull(theMin).myDate) && myDate.isBefore(Objects.requireNonNull(theMax).myDate);
	}

	/**
	 * 
	 * @param theDate a date
	 * @return true if this date is after the date
	 * @throws NullPointerException if the date is null
	 */
	public boolean after(final JobDateTime theDate) {
		return myDate.isAfter(Objects.requireNonNull(theDate).myDate);
	}

	/**
	 * Checks whether this date is before a date.
	 * @param theDate a date
	 * @return true if this date is before the date
	 * @throws NullPointerException if the date is null
	 */
	public boolean before(final JobDateTime theDate) {
		return myDate.isBefore(Objects.requireNonNull(theDate).myDate);
	}

	/**
	 * Outputs this job date as a time string.
	 * @return the time string
	 */
	public String toTimeString() {
		return myTimeFormatter.format(myDate);
	}

	/**
	 * Outputs this job date as a date string.
	 * @return the date string
	 */
	public String toDateString() {
		return myDateFormatter.format(myDate);
	}

	@Override
	public boolean equals(final Object theObject) {
		return theObject instanceof JobDateTime && myDate.equals(((JobDateTime) theObject).myDate);
	}

	@Override
	public int hashCode() {
		return myDate.hashCode();
	}

	@Override
	public int compareTo(final JobDateTime date) {
		return myDate.compareTo(date.myDate);
	}
	
	/**
	 * Checks if two date ranges intersect each other.
	 * @param theStartDate1 the start of the first date range
	 * @param theEndDate1 the end of the first date range
	 * @param theStartDate2 the start of the second date range
	 * @param theEndDate2 the end of the second date range
	 * @return true if the date ranges intersect each other
	 */
	public static boolean intersects(JobDateTime theStartDate1, JobDateTime theEndDate1, JobDateTime theStartDate2,
			JobDateTime theEndDate2) {
		return theStartDate1.between(theStartDate2, theEndDate2)
				|| theEndDate1.between(theStartDate2, theEndDate2)
				|| sameDates(theStartDate1, theStartDate2)
				|| sameDates(theStartDate1, theEndDate2)
				|| sameDates(theEndDate1, theStartDate2)
				|| sameDates(theEndDate1, theEndDate2);
	}

}
