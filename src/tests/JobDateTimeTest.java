/** TCSS 360 - Group 1 */
package tests;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import model.JobDateTime;

/**
 * Tests for {@link model.JobDateTime}.
 * @author Steven Smith
 * @version 1.0
 */
public class JobDateTimeTest {

	private JobDateTime myJobDate;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myJobDate = new JobDateTime();
	}

	@Test
	public final void GetStartOfDate_AnyDate_12AMMidnightTimeString() {
		assertEquals(myJobDate.getStartOfDate().toTimeString(), "12:00 AM");
	}

	@Test(expected = NullPointerException.class)
	public final void SetTime_NullParameter_NullPointerExceptionThrown() {
		myJobDate.setTime(null);
	}

	@Test(expected = NullPointerException.class)
	public final void SameDates_EitherParameterNull_NullPointerExceptionThrown() {
		JobDateTime.sameDates(null, new JobDateTime());
		JobDateTime.sameDates(new JobDateTime(), null);
	}
	
	@Test
	public final void SamesDates_BothCurrentDate_True() {
		assertTrue(JobDateTime.sameDates(new JobDateTime().getStartOfDate(), new JobDateTime().getStartOfDate()));
	}
	
	@Test
	public final void SameDates_TodayAndTomorrow_False() {
		assertFalse(JobDateTime.sameDates(new JobDateTime().getStartOfDate(), new JobDateTime().addDays(1)));
	}

	@Test
	public final void SetFromTimeString_WellFormattedString_ParsedCorrectly() {
		try {
			myJobDate.setFromTimeString("2:11 am");
			assertEquals(myJobDate.toTimeString(), "02:11 AM");
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = ParseException.class)
	public final void SetGetFromTimeString_PoorlyFormattedString_ParseExceptionThrown() throws ParseException {
		myJobDate.setFromTimeString("11:11AM");
	}

	@Test
	public final void SetFromDateString_WellFormattedString_ParsedCorrectly() {
		try {
			myJobDate.setFromDateString("2/2/2019");
			assertEquals(myJobDate.toDateString(), "02/02/2019");
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = ParseException.class)
	public final void SetFromDateString_PoorlyFormattedString_ParseExceptionThrown() throws ParseException {
		myJobDate.setFromDateString("20//2017");
	}

	@Test
	public final void Between_TodayBetweenYesterdayAndTomorrow_True() {
		assertTrue(myJobDate.between(new JobDateTime().addDays(-1), new JobDateTime().addDays(1)));
	}
	
	@Test
	public final void Between_TodayBetweenTomorrowAndTheDayAfter_False() {
		assertFalse(myJobDate.between(new JobDateTime().addDays(1), new JobDateTime().addDays(2)));
	}
	
	@Test
	public final void Between_TodayBetweenTodayAndTomorrow_BoundaryDatesNotIncludedSoFalse() {
		assertFalse(myJobDate.between(new JobDateTime(), new JobDateTime().addDays(1)));
	}
	
	@Test(expected = NullPointerException.class)
	public final void testAfterExpectedNullPointerExceptionOnNullParameter() {
		myJobDate.after(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testBeforeExpectedNullPointerExceptionOnNullParameter() {
		myJobDate.before(null);
	}

	@Test
	public final void Equals_GetStartOfDateTodayBoth_True() {
		assertEquals(myJobDate.getStartOfDate(), myJobDate.getStartOfDate());
	}
	
	@Test
	public final void Equals_NullParameter_False() {
		assertFalse(myJobDate.getStartOfDate().equals(null));
	}
	
	@Test(expected = NullPointerException.class)
	public final void Intersects_NullParameter_NullPointerExceptionThrown() {
		JobDateTime.intersects(null, null, null, null);
	}
	
	@Test
	public final void Intersects_IntersectingDateRanges_True() {
		assertTrue(JobDateTime.intersects(new JobDateTime(), new JobDateTime().addDays(2), new JobDateTime().addDays(1), new JobDateTime().addDays(3)));
	}
	
	@Test
	public final void Intersects_NonIntersectingDateRanges_False() {
		assertFalse(JobDateTime.intersects(new JobDateTime(), new JobDateTime().addDays(1), new JobDateTime().addDays(2), new JobDateTime().addDays(3)));
	}

}
