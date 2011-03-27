package com.hardcoresoft.has.datastorage;
import java.util.Date;

public class HVACScheduleNode {
	
	//Global variables
	
	private Date oDate;
	private double nDesiredTemperature;
	
	//Constructors
	public HVACScheduleNode(Date date, double desiredTemp) {
		oDate = date;
		nDesiredTemperature = desiredTemp;
	}
	
	//Getters and setters
	
	public Date getDate(){
		return oDate;
	}
	
	public double getDesiredTemp(){
		return nDesiredTemperature;
	}
	
	@Override public boolean equals(Object obj) {
		//self comparison check
		if( this == obj ) return true;
		if ( !(obj instanceof HVACScheduleNode) ) return false;
		//cast to native object is now safe
		HVACScheduleNode oObj = (HVACScheduleNode)obj;
		//appropriate field by field comparison can be made
	    if( oObj.getDate().equals(this.getDate()) ){
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
	    result.append("Date: " + this.getDate() + " Temperature: " + this.getDesiredTemp() + NEW_LINE);
	    return result.toString();
	}
}
