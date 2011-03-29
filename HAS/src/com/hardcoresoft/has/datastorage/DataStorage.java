package com.hardcoresoft.has.datastorage;

public class DataStorage {
	// Member variables
	LightingDataController oLightingData;
	HVACDataController oHVACData;
	UserDataController oUserData;
	SecurityDataController oSecurityData;

	private static final DataStorage ref = new DataStorage();
	
	private DataStorage() {
		initDataStorage();
	}

	public static DataStorage getInstance() {
		return ref;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public LightingDataController getoLightingData() {
		return oLightingData;
	}

	public void setoLightingData(LightingDataController oLightingData) {
		this.oLightingData = oLightingData;
	}

	public HVACDataController getoHVACData() {
		return oHVACData;
	}

	public void setoHVACData(HVACDataController oHVACData) {
		this.oHVACData = oHVACData;
	}

	public UserDataController getoUserData() {
		return oUserData;
	}

	public void setoUserData(UserDataController oUserData) {
		this.oUserData = oUserData;
	}

	public SecurityDataController getoSecurityData() {
		return oSecurityData;
	}

	public void setoSecurityData(SecurityDataController oSecurityData) {
		this.oSecurityData = oSecurityData;
	}

	/**
	 * Function: public void initDataStorage() Parameters: N/A Description:
	 * Reads each XML file and prints out data to console for debugging.
	 */
	public void initDataStorage() {
		String NEW_LINE = System.getProperty("line.separator");
		System.out.println("Initializing HVAC Data...");
		oHVACData = new HVACDataController();
		oHVACData.readHVACData();
		System.out.println(oHVACData.oHVACData.toString() + NEW_LINE);
		System.out.println("Initializing Lighting Data...");
		oLightingData = new LightingDataController();
		oLightingData.readLightingData();
		System.out.println(oLightingData.oLightingData.toString() + NEW_LINE);
		System.out.println("Initializing Security Data...");
		oSecurityData = new SecurityDataController();
		oSecurityData.readSecurityData();
		System.out.println(oSecurityData.oSecurityData.toString() + NEW_LINE);
		System.out.println("Initializing User Data...");
		oUserData = new UserDataController();
		oUserData.readUserData();
		System.out.println(oUserData.oUserData.toString() + NEW_LINE);
		resetConnectedVariables();
	}
	
	/**
	 * Function: private void resetConnectedVariables() 
	 * Parameters: N/A 
	 * Description: On system startup, reset the "connected" elements for each 
	 * component to false. Will be set to true when they connect. The reason for this
	 * is incase the components randomly disconnect.
	 */
	private void resetConnectedVariables(){
		getoHVACData().getoHVACData().setbConnected(false);
		getoHVACData().writeHVACData();
		getoLightingData().getoLightingData().setbConnected(false);
		getoLightingData().writeLightingData();
		getoSecurityData().getoSecurityData().setbConnected(false);
		getoLightingData().writeLightingData();
	}

	public void updateUserData() {
		oUserData.writeUserData();
	}

	public void updateHVACData() {
		oHVACData.writeHVACData();
	}

	public void updateLightingData() {
		oLightingData.writeLightingData();
	}

	public void updateSecurityData() {
		oSecurityData.writeSecurityData();
	}

}
