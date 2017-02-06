
/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */

package model;

/**
 * User Controller
 * @author Bryce
 * @version 1
 *
 */
public class UserController extends AbstractController<User> {
	

		public void addUser(final AbstractUser theUser){
			add((User) theUser);
		}
	
		
		public AbstractUser getUser(final String theUser) {
			
			return myList.get(theUser);
		}
	
	
}
