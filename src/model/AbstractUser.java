
/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */

package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract User Class
 * @author Bryce Anderson
 * @version 1
 */

public abstract class AbstractUser implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 973482604419746803L;
	
	/**
	 * Types of users.
	 */
	public enum Type { Volunteer, StaffMember, ParkManager, Guest };

	private String UserName;
	private Type UserType;

	protected AbstractUser(String theUserName, Type theUserType) {
		
		UserName = Objects.requireNonNull(theUserName);
		UserType = Objects.requireNonNull(theUserType);
		
	}
	
	protected void login(){
		
	}
	
	protected void logout(){
		
	}
	
	protected void setUserName(String theName){
		UserName = theName;
	}
	
	protected Type getUserType(){
		return UserType;
	}
	
	protected String getUserName() {
		return UserName;
	}
	

	protected void getOptions() {
		
	}
	
	public String toString(){
		
		
		return "User Name: "+ UserName + " User Type: " + UserType;
		
	}
	
	}