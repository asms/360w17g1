/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package users;

public abstract class User {

	public static final class GUEST extends User {
		private static final GUEST instance = new GUEST();
		private GUEST() {
			super("Guest");
		}
		public static GUEST getInstance() {
			return instance;
		}
	};
	private final String myName;
	
	public User(final String theName) {
		myName = theName;
	}

	public String getName() {
		return myName;
	}

}
