package com.hardcoresoft.has.datastorage;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Function: public class HVACSchedule
 * Description: Container class for storing HVAC schedule info. 
 */
public class HVACSchedule {

	//Global variables
	List<HVACScheduleNode> oSchedule;
	
	//Constructors
	
	public HVACSchedule(){
		oSchedule = new LinkedList<HVACScheduleNode>();
	}
	
	//Properties
	
	//Methods
	
	public List<HVACScheduleNode> getoSchedule() {
		return oSchedule;
	}

	/**
	 * Function: public Boolean addScheduledAction(HVACScheduleNode scheduleNode)
	 * Parameters: HVACSchedule scheduleNode - the HVAC schedule node to be added. 
	 * Description: Adds a new HVACScheduleNode, if it already contains that 
	 * date/time, false is returned.
	 */
	public Boolean addScheduledAction(HVACScheduleNode scheduleNode){
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
	public HVACScheduleNode getNextScheduledAction(Date currentDate){
		return null;
	}
	
	/**
	 * Function: public Boolean updateScheduledAction(Date date, double desiredTemp)
	 * Parameters: Data date - date of the node to be updated. double desiredTemp - new temperature.
	 * Description: Removes the node with the provided date and subsequently replaces it with a new node
	 * with new temperature.
	 */
	public Boolean updateScheduledAction(Date date, double desiredTemp){
		try{
			if(deleteScheduledAction(date) == true){
				HVACScheduleNode oNewNode = new HVACScheduleNode(date, desiredTemp);
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
			HVACScheduleNode oCompare = new HVACScheduleNode(date, 0);
			return oSchedule.remove(oCompare);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Function: public HVACScheduleNode findScheduledAction(Date date)
	 * Parameters: Data date - date of the node to be removed.
	 * Description: Finds the node with the provided date. Returns NULL if not found. 
	 */
	public HVACScheduleNode findScheduledAction(Date date){
		try{
			HVACScheduleNode oCompare = new HVACScheduleNode(date, 0);
			ListIterator<HVACScheduleNode> itr = oSchedule.listIterator();
			while(itr.hasNext()){
				HVACScheduleNode oItr = itr.next();
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
		    ListIterator<HVACScheduleNode> itr = oSchedule.listIterator();
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
}
