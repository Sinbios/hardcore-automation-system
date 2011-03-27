package com.hardcoresoft.has.datastorage;

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

}
