package com.hardcoresoft.has.datastorage;

import java.util.Date;

public class SecurityScheduleNode {
	
//Global variables
	
	private Date oDate;
	private SecurityStatusEnum oSecurityStatus;
	
	//Constructors
	public SecurityScheduleNode(Date date, SecurityStatusEnum securitystatus) {
		oDate = date;
		oSecurityStatus = securitystatus;
	}
	
	//Getters and setters
	
	public Date getDate(){
		return oDate;
	}
	
	public SecurityStatusEnum getSecurityStatus(){
		return oSecurityStatus;
	}
	
	@Override public boolean equals(Object obj) {
		//self comparison check
		if( this == obj ) return true;
		if ( !(obj instanceof SecurityScheduleNode) ) return false;
		//cast to native object is now safe
		SecurityScheduleNode oObj = (SecurityScheduleNode)obj;
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
	    result.append("Date: " + this.getDate() + " Mode: " + this.getSecurityStatus() + NEW_LINE);
	    return result.toString();
	}

}
