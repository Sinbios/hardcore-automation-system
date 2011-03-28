package com.hardcoresoft.has.datastorage;

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
	
	
}
