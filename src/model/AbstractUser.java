
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
	private String userName;
	private String userType;

	protected AbstractUser(String theUserName, String theUserType) {
		
		userName = Objects.requireNonNull(theUserName);
		userType = Objects.requireNonNull(theUserType);
		
	}
	
	protected void login(){
		
	}
	
	protected void logout(){
		
	}
	
	protected void setUserName(String theName){
		userName = theName;
	}
	
	protected void setUserType(String theType){
		userType = theType;
	}
	
	protected String getUserType(){
		return userType;
	}
	
	protected String getUserName() {
		return userName;
	}
	
	protected void getOptions() {
		
	}
	
	public String toString(){
		
		
		return "User Name: "+ userName + " User Type: " + userType;
		
	}
	
	}