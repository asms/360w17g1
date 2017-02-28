package collection;
/* TCSS 360 - 2nd Unit Testing Assignment */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

/**
 * Test for {@link Collections#disjoint(java.util.Collection, java.util.Collection)}.
 * @author Steven Smith
 * @version 0.1-alpha
 */
public final class CollectionsTest {
	
	@Test
	public void Disjoint_CollectionsBothEmpty_True() {
		final Set<Object> object1 = new HashSet<Object>();
		final Set<Object> object2 = new HashSet<Object>();
		assertTrue(Collections.disjoint(object1, object2));
	}
	
	@Test
	public void Disjoint_OneCollectionEmpty_True() {
		final Set<Object> object1 = new HashSet<Object>();
		final Set<Object> object2 = new HashSet<Object>();
		object2.add(new Object());
		assertTrue(Collections.disjoint(object1, object2));
	}
	
	@Test
	public void Disjoint_NonEmptyNoElementsInCommon_True() {
		final Set<Object> object1 = new HashSet<Object>();
		final Set<Object> object2 = new HashSet<Object>();
		object1.add(new Object());
		object2.add(new Object());
		assertTrue(Collections.disjoint(object1, object2));
	}
	
	@Test
	public void Disjoint_ElementInCommonEmptyOtherwise_False() {
		final Set<Object> object1 = new HashSet<Object>();
		final Set<Object> object2 = new HashSet<Object>();
		final Object commonObject = new Object();
		object1.add(commonObject);
		object2.add(commonObject);
		assertFalse(Collections.disjoint(object1, object2));
	}
	
	@Test
	public void Disjoint_ElementInCommonDisjointOtherwise_False() {
		final Set<Object> object1 = new HashSet<Object>();
		final Set<Object> object2 = new HashSet<Object>();
		final Object commonObject = new Object();
		object1.add(commonObject);
		object1.add(new Object());
		object2.add(commonObject);
		object2.add(new Object());
		assertFalse(Collections.disjoint(object1, object2));
	}
	
	@Test
	public void Disjoint_DifferentCollectionTypesElementInCommon_False() {
		final Set<Object> object1 = new HashSet<Object>();
		final List<Object> object2 = new ArrayList<Object>();
		final Object commonObject = new Object();
		object1.add(commonObject);
		object2.add(commonObject);
		assertFalse(Collections.disjoint(object1, object2));
	}
	
	@Test
	public void Disjoint_DifferentCollectionTypesNoElementsInCommon_True() {
		final Set<Object> object1 = new HashSet<Object>();
		final List<Object> object2 = new ArrayList<Object>();
		object1.add(new Object());
		object2.add(new Object());
		assertTrue(Collections.disjoint(object1, object2));
	}
	
	@Test
	public void Disjoint_SameCollectionEmpty_True() {
		final Set<Object> object1 = new HashSet<Object>();
		assertTrue(Collections.disjoint(object1, object1));
	}
	
	@Test
	public void Disjoint_SameCollectionNonEmpty_False() {
		final Set<Object> object1 = new HashSet<Object>();
		object1.add(new Object());
		assertFalse(Collections.disjoint(object1, object1));
	}
	
	@Test(expected = NullPointerException.class)
	public void Disjoint_NullAsFirstParam_NullPointerExceptionThrown() {
		Collections.disjoint(null, Collections.EMPTY_SET);
	}
	
	@Test(expected = NullPointerException.class)
	public void Disjoint_NullAsSecondParam_NullPointerExceptionThrown() {
		Collections.disjoint(Collections.EMPTY_SET, null);
	}
}
