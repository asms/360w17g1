
package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * Test Suite for all automated tests.
 * @author Bryce
 * @version 1
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
	
	//Abstract tests
	AbstractUserTest.class,
	AbstractControllerTest.class,
	JobControllerTest.class,
	ParkControllerTest.class,
	UserControllerTest.class,
	
	JobTest.class,
	ParkTest.class,
	ParkManagerTest.class,
	VolunteerTest.class,
	
	JobDateTimeTest.class
   
})



public class TestSuite {

}
