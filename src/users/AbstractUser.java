/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */

import java.io.Serializable;

/**
 *  * Abstract User Class
 * @author Bryce Anderson
 * @version 1
 */

public abstract class AbstractUser implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 973482604419746803L;
	String userName;
	String userType;

	private void login(){
		
	}
	
	private void logout(){
		
	}
	
	private void setUserName(String theName){
		userName = theName;
	}
	
	private void setUserType(String theType){
		userType = theType;
	}
	
	private void getOptions() {
		
	}
	
	public String toString(){
		
		
		return "User Name: "+ userName + " User Type: " + userType;
		
	}
	
	}

