package com.hardcoresoft.has.datastorage;

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
	
	
}
