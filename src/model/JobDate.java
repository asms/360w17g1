/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.io.Serializable;
import java.text.ParseException;
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
public class JobDate implements Serializable, Comparable<JobDate> {

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
	public JobDate(final LocalDateTime theDate) {
		myDate = Objects.requireNonNull(theDate);
		myDateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		myTimeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
	}

	/**
	 * Creates a new jobs date at the current date and time.
	 */
	public JobDate() {
		this(LocalDateTime.now());
	}

	/**
	 * Gets the start of this date as a separate object.
	 * @return the beginning of the this date
	 */
	public JobDate getStartOfDate() {
		return new JobDate(myDate.with(LocalTime.MIN));
	}

	/**
	 * Sets this date's time to the time parameter.
	 * @param theTime a date object with a specific time
	 * @return a reference to this job date for chaining
	 * @throws NullPointerException if the time is null
	 */
	public JobDate setTime(final LocalDateTime theTime) {
		myDate = LocalDateTime.of(myDate.toLocalDate(), theTime.toLocalTime());
		return this;
	}

	/**
	 * Adds years to this date.
	 * @param theYears the number of years to add
	 * @return a reference to this job date for chaining
	 */
	public JobDate addYears(final int theYears) {
		myDate = myDate.plusYears(theYears);
		return this;
	}

	/**
	 * Adds months to this date.
	 * @param theMonths the number of months to add
	 * @return a reference to this job date for chaining
	 */
	public JobDate addMonths(final int theMonths) {
		myDate = myDate.plusMonths(theMonths);
		return this;
	}

	/**
	 * Adds days to this date.
	 * @param theDays the number of days to add
	 * @return a reference to this job date for chaining
	 */
	public JobDate addDays(final int theDays) {
		myDate = myDate.plusDays(theDays);
		return this;
	}

	/**
	 * Adds hours to this date
	 * @param theHours the number of hours to add
	 * @return a reference to this job date for chaining
	 */
	public JobDate addHours(final int theHours) {
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
		myDate = LocalDateTime.of(myDate.toLocalDate(), LocalTime.parse(timeString, myTimeFormatter));
		return this;
	}

	/**
	 * Sets the current date from the date string.
	 * @param dateString a string in the format MM/dd/yyyy
	 * @return a reference to this job date for chaining
	 * @throws ParseException if the string is not well formed
	 * @throws NullPointerException if the date string is null
	 */
	public JobDate setFromDateString(final String dateString) throws ParseException {
		myDate = LocalDateTime.of(LocalDate.parse(dateString, myDateFormatter), myDate.toLocalTime());
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
		return myDate.isAfter(Objects.requireNonNull(theMin).myDate) && myDate.isBefore(Objects.requireNonNull(theMax).myDate);
	}

	/**
	 * 
	 * @param theDate a date
	 * @return true if this date is after the date
	 * @throws NullPointerException if the date is null
	 */
	public boolean after(final JobDate theDate) {
		return myDate.isAfter(Objects.requireNonNull(theDate).myDate);
	}

	/**
	 * Checks whether this date is before a date.
	 * @param theDate a date
	 * @return true if this date is before the date
	 * @throws NullPointerException if the date is null
	 */
	public boolean before(final JobDate theDate) {
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
