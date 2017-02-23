/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import model.JobDate;

/**
 * @author Steven
 *
 */
public class JobDateTest {

	private JobDate myJobDate;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myJobDate = new JobDate();
	}

	/**
	 * Test method for {@link model.JobDate#getStartOfDate()}.
	 */
	@Test
	public final void testGetStartOfDate() {
		assertEquals(myJobDate.getStartOfDate().toTimeString(), "12:00 AM");
	}

	/**
	 * Test method for {@link model.JobDate#setTime(java.util.Date)}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testSetTime() {
		myJobDate.setTime(null);
	}

	/**
	 * Test method for {@link model.JobDate#addYears(int)}.
	 */
	@Test
	public final void testAddYears() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		final int nextYear = cal.get(Calendar.YEAR);
		assertEquals(Integer.valueOf(myJobDate.addYears(1).toDateString().split("/")[2]).intValue(), nextYear);
	}

	/**
	 * Test method for {@link model.JobDate#addMonths(int)}.
	 */
	@Test
	public final void testAddMonths() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		final int nextMonth = cal.get(Calendar.MONTH) + 1; // Add one because calendar stores months as month-1.
		assertEquals(Integer.valueOf(myJobDate.addMonths(1).toDateString().split("/")[0]).intValue(), nextMonth);
	}

	/**
	 * Test method for {@link model.JobDate#addDays(int)}.
	 */
	@Test
	public final void testAddDays() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		final int nextDate = cal.get(Calendar.DATE);
		assertEquals(Integer.valueOf(myJobDate.addDays(1).toDateString().split("/")[1]).intValue(), nextDate);
	}

	/**
	 * Test method for {@link model.JobDate#sameDates(model.JobDate, model.JobDate)}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testSameDatesExpectsNullPointerExceptionWhenParametersAreNull() {
		JobDate.sameDates(null, new JobDate());
		JobDate.sameDates(new JobDate(), null);
	}
	
	
	/**
	 * Test method for {@link model.JobDate#sameDates(model.JobDate, model.JobDate)}.
	 */
	@Test
	public final void testSameDatesExpectsTrueWhenDatesAreSame() {
		assertTrue(JobDate.sameDates(new JobDate().getStartOfDate(), new JobDate().getStartOfDate()));
	}
	
	/**
	 * Test method for {@link model.JobDate#sameDates(model.JobDate, model.JobDate)}.
	 */
	@Test
	public final void testSameDatesExpectsFalseWhenDatesAreNotSame() {
		assertFalse(JobDate.sameDates(new JobDate().getStartOfDate(), new JobDate().addDays(1)));
	}

	/**
	 * Test method for {@link model.JobDate#setFromTimeString(java.lang.String)}.
	 */
	@Test
	public final void testSetFromTimeString() {
		try {
			myJobDate.setFromTimeString("11:11 AM");
			assertEquals(myJobDate.toTimeString(), "11:11 AM");
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link model.JobDate#setFromDateString(java.lang.String)}.
	 */
	@Test
	public final void testSetFromDateString() {
		try {
			myJobDate.setFromDateString("02/02/2222");
			assertEquals(myJobDate.toDateString(), "02/02/2222");
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link model.JobDate#between(model.JobDate, model.JobDate)}.
	 */
	@Test
	public final void testBetweenExpectsTrueWhenBetween() {
		assertTrue(myJobDate.between(new JobDate().addDays(-1), new JobDate().addDays(1)));
	}
	
	/**
	 * Test method for {@link model.JobDate#between(model.JobDate, model.JobDate)}.
	 */
	@Test
	public final void testBetweenExpectsFalseWhenNotBetween() {
		assertFalse(myJobDate.between(new JobDate().addDays(1), new JobDate().addDays(2)));
	}

	/**
	 * Test method for {@link model.JobDate#after(model.JobDate)}.
	 */
	@Test
	public final void testAfter() {
		assertTrue(new JobDate().addDays(1).after(new JobDate()));
	}

	/**
	 * Test method for {@link model.JobDate#before(model.JobDate)}.
	 */
	@Test
	public final void testBefore() {
		assertTrue(new JobDate().before(new JobDate().addDays(1)));
	}
	
	/**
	 * Test method for {@link model.JobDate#after(model.JobDate)}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testAfterExpectedNullPointerExceptionOnNullParameter() {
		myJobDate.after(null);
	}

	/**
	 * Test method for {@link model.JobDate#before(model.JobDate)}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testBeforeExpectedNullPointerExceptionOnNullParameter() {
		myJobDate.before(null);
	}

	/**
	 * Test method for {@link model.JobDate#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObjectExpectsTrueWhenObjectsAreEqual() {
		assertTrue(myJobDate.getStartOfDate().equals(myJobDate.getStartOfDate()));
	}
	
	/**
	 * Test method for {@link model.JobDate#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObjectExpectsFalseWhenObjectsAreEqual() {
		assertFalse(myJobDate.getStartOfDate().equals(myJobDate));
	}

}
