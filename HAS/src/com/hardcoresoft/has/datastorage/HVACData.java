package com.hardcoresoft.has.datastorage;


/**
 * Function: public class HVACData
 * Description: Wrapper class for HVAC data storage.
 */
public class HVACData {
	
	//Member variables
	private String sIpAddress;
	private int nPort;
	private Boolean bConnected;
	private String sMsgQueueName;
	private double nCurrentTemperature;
	private double nDesiredTemperature;
	private HVACSchedule oSchedule;
	private HVACStatus oStatus;
	
	//Constructors
	public HVACData(){
		oSchedule = new HVACSchedule();
	}
	
	public HVACData(String ipAddress, String msgQueueName, int port, Boolean connected, double currentTemp, double desiredTemp ){
		sIpAddress = ipAddress;
		nPort = port;
		bConnected = connected;
		sMsgQueueName = msgQueueName;
		nCurrentTemperature = currentTemp;
		nDesiredTemperature = desiredTemp; 
		oSchedule = new HVACSchedule(); 
	}
	
	//Getters & setters
	
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

	public double getnCurrentTemperature() {
		return nCurrentTemperature;
	}

	public void setnCurrentTemperature(double nCurrentTemperature) {
		this.nCurrentTemperature = nCurrentTemperature;
	}

	public double getnDesiredTemperature() {
		return nDesiredTemperature;
	}

	public void setnDesiredTemperature(double nDesiredTemperature) {
		this.nDesiredTemperature = nDesiredTemperature;
	}

	public HVACSchedule getoSchedule() {
		return oSchedule;
	}

	public void setoSchedule(HVACSchedule oSchedule) {
		this.oSchedule = oSchedule;
	}

	public String getHVACIpAddress(){
		return sIpAddress;
	}

	public HVACStatus getoStatus() {
		return oStatus;
	}

	public void setoStatus(HVACStatus oStatus) {
		this.oStatus = oStatus;
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
	    result.append("<tns:currentTemperature>" + Double.toString(this.nCurrentTemperature) + "</tns:currentTemperature>" + NEW_LINE);
	    result.append("<tns:desiredTemperature>" + Double.toString(this.nDesiredTemperature) + "</tns:desiredTemperature>" + NEW_LINE);
	    result.append("<tns:status>" + Integer.toString(this.oStatus.ordinal()) + "</tns:status>" + NEW_LINE);
	    result.append("<tns:schedule>" + NEW_LINE);
	    result.append(this.getoSchedule().toString());
	    result.append("</tns:schedule>");
	    return result.toString();
	}
	
	
}
