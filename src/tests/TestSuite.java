
package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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
	StaffMemberTest.class,
	VolunteerTest.class
   
})



public class TestSuite {

}
