package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * User Class
 * @author Bryce
 * @version 1
 */
public class User implements Serializable, UniqueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String FirstName;
	private String LastName;

	private String UserName;

	protected User(final String theUserName) {
		UserName = Objects.requireNonNull(theUserName);
		
	}
	
	public String getUserName() {
		return UserName;
	}
	

	public void getOptions() {
		
	}
	
	protected void login(String theUserName){
		
		
	}
	
	public String getFirstName() {
		return FirstName;
	}
	
	public String getLastName() {
		return LastName;
	}


	@Override
	public String getKey() {
		return UserName;
	}

	@Override
	public String toString(){
		return "User Name: "+ UserName + " User Type: " + getClass().getSimpleName();
	}
	
	
	@Override
	public boolean equals(final Object theObject) {
		
		return (theObject instanceof User) 
				&& (getKey().equals(((User)theObject).getKey())
				&& (getClass().getSimpleName().equals(((User) theObject).getClass().getSimpleName())));
	}




	@Override
	public int hashCode() {
		return Objects.hash(getKey(), getClass().getSimpleName(), FirstName, LastName);
	}
		

	
}
