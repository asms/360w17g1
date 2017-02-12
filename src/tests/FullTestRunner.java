package tests;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Runs all the tests in the test suite
 * @author Bryce
 * @version 1
 */
public class FullTestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(TestSuite.class);

      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println("The tests all passed: " + result.wasSuccessful());
   }
} 

