/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

import model.AbstractController;
import model.UniqueObject;

/**
 * Tests the AbstractController class.
 * 
 * <p>
 * The primary use for the abstract controller class is to control serialization and deserialization.
 * </p>
 * @author Steven Smith
 * @version 1.0
 *
 */
public class AbstractControllerTest {
	
	private ConcreteController<AbstractControllerTest.UniqueObjectTest> myController;

	@Before
	public void setUp() {
		myController = new ConcreteController<AbstractControllerTest.UniqueObjectTest>();
	}
	
	/*
	 * Currently this does not pass because AbstractController is not complete.
	 */
	@Test
	public void testSerialization() {
		final String random = String.valueOf(Math.random());
		UniqueObjectTest testObject = new UniqueObjectTest(random);
		myController.addObject(testObject);
		myController = new ConcreteController<AbstractControllerTest.UniqueObjectTest>();
		assertTrue(myController.getObjects().containsKey(random));
	}
	
	public class UniqueObjectTest implements UniqueObject {
		private final String myKey;
		public UniqueObjectTest(final String theKey) {
			myKey = theKey;
		}
		@Override
		public String getKey() {
			return myKey;
		}
	}
	
	public class ConcreteController<T extends UniqueObject> extends AbstractController<T> {
		
		public ConcreteController() {
			super();
		}
		
		public void addObject(T theObject) {
			add(theObject);
		}
		public HashMap<String, T> getObjects() {
			return (HashMap<String, T>) myList.clone();
		}
	}

}
