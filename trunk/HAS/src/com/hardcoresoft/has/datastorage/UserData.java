package com.hardcoresoft.has.datastorage;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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
