package com.hardcoresoft.has.datastorage;

/**
 * Function: public class UserDataNode
 * Description: Container class for storing user info. 
 */
public class UserDataNode {

	//Member variables
	private String sFirstName;
	private String sLastName;
	private String sUserName;
	private String sPassword;
	private UserPermission oUserPermission;
	
	//Constructors
	public UserDataNode(String firstname, String lastname, String username, String password, UserPermission permission ){
		sFirstName = firstname;
		sLastName = lastname;
		sUserName = username;
		sPassword = password;
		oUserPermission = permission;
	}
	
	public UserDataNode(String username){
		sUserName = username;
	}

	//Getters and setters
	public String getsFirstName() {
		return sFirstName;
	}

	public String getsPassword() {
		return sPassword;
	}

	public void setsFirstName(String sFirstName) {
		this.sFirstName = sFirstName;
	}

	public String getsLastName() {
		return sLastName;
	}

	public void setsLastName(String sLastName) {
		this.sLastName = sLastName;
	}

	public String getsUserName() {
		return sUserName;
	}

	public UserPermission getoUserPermission() {
		return oUserPermission;
	}
	
	//Public methods
	
	/**
	 * Function: public Boolean validatePassword(String password)
	 * Parameters: String password - password attempt to validate.
	 * Description: Returns true if password is correct, false if incorrect.
	 */
	public Boolean validatePassword(String password){
		try{
			if(password.equals(sPassword)){
				return true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Function: public Boolean changePassword(String currentPassword, String newPassword)
	 * Parameters: String currentPassword - current PW, String newPassword - new PW.
	 * Description: Returns true if current password is correct, false if incorrect. If 
	 * correct, changes the password to the new password.  
	 */
	public Boolean changePassword(String currentPassword, String newPassword){
		try{
			if(validatePassword(currentPassword) == true){
				sPassword = newPassword;
				return true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public void setoUserPermission(UserPermission oUserPermission) {
		this.oUserPermission = oUserPermission;
	}
	
	@Override public boolean equals(Object obj) {
		//self comparison check
		if( this == obj ) return true;
		if ( !(obj instanceof UserDataNode) ) return false;
		//cast to native object is now safe
		UserDataNode oObj = (UserDataNode)obj;
		//appropriate field by field comparison can be made
	    if( oObj.sUserName.equals(this.sUserName) ){
	    	return true;
	    }
	    else{
	    	return false;
	    }
	}
	
	//toString for debugging.
	@Override public String toString() {
		StringBuilder result = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");
	    result.append(this.getClass().getName() + " Object {" + NEW_LINE);
	    result.append("<tns:firstName>" + this.sFirstName + "</tns:firstName>" + NEW_LINE);
	    result.append("<tns:lastName>" + this.sLastName + "</tns:lastName>" + NEW_LINE);
	    result.append("<tns:userName>" + this.sUserName + "</tns:userName>" + NEW_LINE);
	    result.append("<tns:password>" + this.sPassword + "</tns:password>" + NEW_LINE);
	    result.append("<tns:permission>" + this.oUserPermission.ordinal() + "</tns:permission>" + NEW_LINE);
	    return result.toString();
	}
}
