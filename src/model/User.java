package model;

import java.io.Serializable;

/**
 * User Class
 * @author Bryce
 * @version 1
 */
public class User extends AbstractUser implements Serializable, UniqueObject {

	/**
	 * 
	 */
	private String FirstName;
	private String LastName;
	private static final long serialVersionUID = 3776956632727737392L;

	protected User(String theUserName, String theUserType) {
		super(theUserName, theUserType);
		
	}

	
	protected void login(String theUserName){
		
		
	}
	
	protected String getFirstName() {
		return FirstName;
	}
	
	protected String getLastName() {
		return LastName;
	}


	@Override
	public String getKey() {
		
		return getUserName();
	}


	public String getVolunteer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
