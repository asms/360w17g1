package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * User Class
 * @author Bryce
 * @version 1
 */
public abstract class AbstractUser implements Serializable, UniqueObject {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 4039931473200511934L;
	/**
	 * 
	 */
	private String FirstName;
	private String LastName;

	private final String UserName;

	public AbstractUser(final String theUserName) {
		UserName = Objects.requireNonNull(theUserName);
		
	}
	
	public String getUserName() {
		return UserName;
	}
		
	
	public String getFirstName() {
		return FirstName;
	}
	
	public String getLastName() {
		return LastName;
	}


	public void setFirstName(String theName) {
		FirstName = theName;
	}
	
	public void setLastName(String theName) {
		LastName = theName;
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
		
		return (theObject instanceof AbstractUser) 
				&& (getKey().equals(((AbstractUser)theObject).getKey())
				&& (getClass().getSimpleName().equals(((AbstractUser) theObject).getClass().getSimpleName())));
	}




	@Override
	public int hashCode() {
		return Objects.hash(getKey(), getClass().getSimpleName(), FirstName, LastName);
	}
		

	
}
