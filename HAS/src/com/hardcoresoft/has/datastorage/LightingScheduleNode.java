package com.hardcoresoft.has.datastorage;

import java.util.Date;

/**
 * Function: public class LightingScheduleNode
 * Description: Container class for storing Lighting schedule node info. 
 */
public class LightingScheduleNode {
	
	//Global variables
	
	private Date oDate;
	private Boolean bStatus;
	private int nBrightness;
	private int nColourTemp;
	


	//Constructors
	public LightingScheduleNode(Date date, Boolean status, int brightness, int colourtemp) {
		oDate = date;
		bStatus = status; 
		nBrightness = brightness;
		nColourTemp = colourtemp;
	}

	//Getters
	public Date getDate() {
		return oDate;
	}

	public int getnBrightness() {
		return nBrightness;
	}

	public int getnColourTemp() {
		return nColourTemp;
	}
	
	public Boolean getbStatus() {
		return bStatus;
	}

	@Override public boolean equals(Object obj) {
		//self comparison check
		if( this == obj ) return true;
		if ( !(obj instanceof LightingScheduleNode) ) return false;
		//cast to native object is now safe
		LightingScheduleNode oObj = (LightingScheduleNode)obj;
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
	    result.append("<tns:date>" + this.getDate() + "</tns:date>" + NEW_LINE);
	    result.append("<tns:status>" + Boolean.toString(this.getbStatus()) + "</tns:status>" + NEW_LINE);
	    result.append("<tns:brightness>" + Integer.toString(this.nBrightness) + "</tns:brightness>" + NEW_LINE);
	    result.append("<tns:colourTemp>" + Integer.toString(this.nColourTemp) + "</tns:colourTemp>" + NEW_LINE);
	    return result.toString();
	}
	

}
