package com.hardcoresoft.has.datastorage;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Function: public class SecuritySchedule
 * Description: Container class for storing security schedule info. 
 */
public class SecuritySchedule {
	
	//Global variables
	List<SecurityScheduleNode> oSchedule;
	
	//Constructors
	
	public SecuritySchedule(){
		oSchedule = new LinkedList<SecurityScheduleNode>();
	}
	
	//Properties
	
	//Methods
	
	public List<SecurityScheduleNode> getoSchedule() {
		return oSchedule;
	}

	/**
	 * Function: public Boolean addScheduledAction(SecurityScheduleNode scheduleNode)
	 * Parameters: SecurityScheduleNode scheduleNode - the Lighting schedule node to be added. 
	 * Description: Adds a new LightingScheduleNode, if it already contains that 
	 * date/time, false is returned.
	 */	
	public Boolean addScheduledAction(SecurityScheduleNode scheduleNode){
		try
		{
			if(oSchedule.contains(scheduleNode))
			{
				return false;
			}
			return oSchedule.add(scheduleNode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	//Do this if needed, don't think it is necessary at the moment. 
	public SecurityScheduleNode getNextScheduledAction(Date currentDate){
		return null;
	}
	
	/**
	 * Function: public Boolean updateScheduledAction(Date date, SecurityMode mode)
	 * Parameters: Data date - date of the node to be updated. SecurityMode mode - new mode. 
	 * Description: Removes the node with the provided date and subsequently replaces it with a new node
	 * with new security mode.
	 */
	public Boolean updateScheduledAction(Date date, SecurityMode mode){
		try{
			if(deleteScheduledAction(date) == true){
				SecurityScheduleNode oNewNode = new SecurityScheduleNode(date, mode);
				return oSchedule.add(oNewNode);
			}
			else{
				return false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Function: public Boolean deleteScheduledAction(Date date)
	 * Parameters: Data date - date of the node to be removed.
	 * Description: Removes the node with the provided date.
	 */ 
	public Boolean deleteScheduledAction(Date date){
		try{
			SecurityScheduleNode oCompare = new SecurityScheduleNode(date, convertIntToSecurityStatus(0));
			return oSchedule.remove(oCompare);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Function: public SecurityScheduleNode findScheduledAction(Date date)
	 * Parameters: Data date - date of the node to be removed.
	 * Description: Finds the node with the provided date. Returns NULL if not found.
	 */
	public SecurityScheduleNode findScheduledAction(Date date){
		try{
			SecurityScheduleNode oCompare = new SecurityScheduleNode(date, convertIntToSecurityStatus(0));
			ListIterator<SecurityScheduleNode> itr = oSchedule.listIterator();
			while(itr.hasNext()){
				SecurityScheduleNode oItr = itr.next();
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
		    ListIterator<SecurityScheduleNode> itr = oSchedule.listIterator();
		    while(itr.hasNext())
		    {
		    	result.append("<tns:action>" + NEW_LINE);
		    	result.append(itr.next());
		    	result.append("</tns:action>" + NEW_LINE);
		    }
		    return result.toString();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static SecurityMode convertIntToSecurityStatus(int value)
	{
		return SecurityMode.class.getEnumConstants()[value];
	}

}
