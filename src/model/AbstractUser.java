
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

	private String UserName;
	private String UserType;

	protected AbstractUser(String theUserName, String theUserType) {
		
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
	
	protected void setUserType(String theType){
		UserType = theType;
	}
	
	protected String getUserType(){
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