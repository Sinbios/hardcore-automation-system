package com.hardcoresoft.has.datastorage;

/**
 * Function: public class SecurityData
 * Description: Wrapper class for security data storage.
 */
public class SecurityData {

	//Member variables
	private String sIpAddress;
	private int nPort;
	private Boolean bConnected;
	private String sMsgQueueName;
	private int nPin;
	private SecurityMode oStatus;
	private SecuritySchedule oSchedule;
	
	//Constructors
	public SecurityData(){
		oSchedule = new SecuritySchedule();
	}

	public String getsIpAddress() {
		return sIpAddress;
	}

	public void setsIpAddress(String sIpAddress) {
		this.sIpAddress = sIpAddress;
	}

	public int getnPort() {
		return nPort;
	}

	public void setnPort(int nPort) {
		this.nPort = nPort;
	}

	public Boolean getbConnected() {
		return bConnected;
	}

	public void setbConnected(Boolean bConnected) {
		this.bConnected = bConnected;
	}

	public String getsMsgQueueName() {
		return sMsgQueueName;
	}

	public void setsMsgQueueName(String sMsgQueueName) {
		this.sMsgQueueName = sMsgQueueName;
	}

	public int getnPin() {
		return nPin;
	}

	public void setnPin(int nPin) {
		this.nPin = nPin;
	}

	public SecurityMode getoStatus() {
		return oStatus;
	}

	public void setoStatus(SecurityMode oStatus) {
		this.oStatus = oStatus;
	}

	public SecuritySchedule getoSchedule() {
		return oSchedule;
	}

	public void setoSchedule(SecuritySchedule oSchedule) {
		this.oSchedule = oSchedule;
	}
	
	//toString for debugging.
	@Override public String toString() {
		StringBuilder result = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");
	    result.append(this.getClass().getName() + " Object {" + NEW_LINE);
	    result.append("<tns:ipAddress>" + this.sIpAddress + "</tns:ipAddress>"+  NEW_LINE);
	    result.append("<tns:port>" + Integer.toString(this.nPort) + "</tns:port>" + NEW_LINE);
	    result.append("<tns:connected>" + Boolean.toString(this.bConnected) + "</tns:connected>" + NEW_LINE);
	    result.append("<tns:msgQueueName>" + this.sMsgQueueName + "</tns:msgQueueName>" + NEW_LINE);
	    result.append("<tns:pin>" + Integer.toString(this.nPin) + "</tns:pin>" + NEW_LINE);
	    result.append("<tns:status>" + Integer.toString(this.oStatus.ordinal()) + "</tns:status>" + NEW_LINE);
	    result.append("<tns:schedule>" + NEW_LINE);
	    result.append(this.getoSchedule().toString());
	    result.append("</tns:schedule>");
	    return result.toString();
	}
	
}
