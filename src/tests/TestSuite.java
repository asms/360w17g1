
package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   AbstractControllerTest.class,
   JobControllerTest.class,
   ParkControllerTest.class,
   UserControllerTest.class,
   UserTest.class,
   JobTest.class,
   ParkTest.class,
   ParkManagerTest.class,
   StaffMemberTest.class,
   VolunteerTest.class
})



public class TestSuite {

}
