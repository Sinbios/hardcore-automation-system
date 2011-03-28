package com.hardcoresoft.has.datastorage;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Function: public class UserData
 * Description: Container class for storing user data. 
 */
public class UserData {

	//Global variables
	List<UserDataNode> oUsers;
	
	//Constructors
	public UserData(){
		oUsers = new LinkedList<UserDataNode>();
	}

	//Properties
	public List<UserDataNode> getoUsers() {
		return oUsers;
	}
	
	//Public methods
	/**
	 * Function: public Boolean addNewUser(UserDataNode user)
	 * Parameters: UserDataNode user - node of the user which needs to be added.
	 * Description: Adds a new user data node. Returns false if that user already exists, based
	 * on username. 
	 */
	public Boolean addNewUser(UserDataNode user){
		try{
			if(findUser(user.getsUserName()) != null){
				return false;
			}
			else{
				oUsers.add(user);
				return true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Function: public UserDataNode findUser(String username)
	 * Parameters: String username - username of the user to be found.
	 * Description: Returns the UserDataNode of a user based on username. If not found, it 
	 * returns NULL. 
	 */
	public UserDataNode findUser(String username){
		try{
			UserDataNode oCompare = new UserDataNode(username);
			ListIterator<UserDataNode> itr = oUsers.listIterator();
			while(itr.hasNext()){
				UserDataNode oItr = itr.next();
				if(oCompare.equals(oItr))
				{
					return oItr;
				}
			}
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	//toString for debugging.
	@Override public String toString() {
		try{
			StringBuilder result = new StringBuilder();
		    String NEW_LINE = System.getProperty("line.separator");
		    result.append(this.getClass().getName() + " Object {" + NEW_LINE);
		    ListIterator<UserDataNode> itr = oUsers.listIterator();
		    while(itr.hasNext())
		    {
		    	result.append("<tns:user>" + NEW_LINE);
		    	result.append(itr.next());
		    	result.append("</tns:user>" + NEW_LINE);
		    }
		    return result.toString();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
