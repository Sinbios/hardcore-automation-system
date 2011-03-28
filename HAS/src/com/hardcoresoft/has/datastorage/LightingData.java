package com.hardcoresoft.has.datastorage;

/**
 * Function: public class LightingData
 * Description: Wrapper class for lighting data storage.
 */
public class LightingData {
	
	//Member variables
	private String sIpAddress;
	private int nPort;
	private Boolean bConnected;
	private String sMsgQueueName;
	private Boolean bOperationalStatus;
	private int nBrightness;
	private int nColourTemp;
	private LightingSchedule oSchedule;
	
	public LightingData(){
		oSchedule = new LightingSchedule();
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

	public Boolean getbOperationalStatus() {
		return bOperationalStatus;
	}

	public void setbOperationalStatus(Boolean bOperationalStatus) {
		this.bOperationalStatus = bOperationalStatus;
	}

	public int getnBrightness() {
		return nBrightness;
	}

	public void setnBrightness(int nBrightness) {
		this.nBrightness = nBrightness;
	}

	public int getnColourTemp() {
		return nColourTemp;
	}

	public void setnColourTemp(int nColourTemp) {
		this.nColourTemp = nColourTemp;
	}

	public LightingSchedule getoSchedule() {
		return oSchedule;
	}

	public void setoSchedule(LightingSchedule oSchedule) {
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
	    result.append("<tns:brightness>" + Integer.toString(this.nBrightness) + "</tns:brightness>" + NEW_LINE);
	    result.append("<tns:colourTemp>" + Integer.toString(this.nColourTemp) + "</tns:colourTemp>" + NEW_LINE);
	    result.append("<tns:status>" + Boolean.toString(this.bOperationalStatus) + "</tns:status>" + NEW_LINE);
	    result.append("<tns:schedule>" + NEW_LINE);
	    result.append(this.getoSchedule().toString());
	    result.append("</tns:schedule>");
	    return result.toString();
	}

}
