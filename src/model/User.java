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
	private static final long serialVersionUID = 3776956632727737392L;

	protected User(String theUserName, String theUserType) {
		super(theUserName, theUserType);
		
	}

	
	protected void login(String theUserName){
		
		
	}


	@Override
	public String getKey() {
		
		return getUserName();
	}
	
	
}
