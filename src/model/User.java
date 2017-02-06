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
	private String FirstName;
	private String LastName;
	
	/**
	 * Types of users.
	 */
	public enum Type { Volunteer, StaffMember, ParkManager, Guest };

	private String UserName;
	private Type UserType;

	protected User(String theUserName, Type theUserType) {
		UserName = Objects.requireNonNull(theUserName);
		UserType = Objects.requireNonNull(theUserType);
		
	}
	
	public Type getUserType(){
		return UserType;
	}
	
	public String getUserName() {
		return UserName;
	}
	

	public void getOptions() {
		
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
		return UserName;
	}

	@Override
	public String toString(){
		return "User Name: "+ UserName + " User Type: " + UserType;
	}
	
	
}
