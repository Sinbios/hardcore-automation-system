package com.hardcoresoft.has.components.lighting;

import com.hardcoresoft.has.components.ComponentMessageListener;
import com.hardcoresoft.has.components.HASComponent;

public class LightingComponent extends HASComponent 
{
	Boolean bStatus = false;
	int nColourTemp = 100;
	int nBrightness= 100;
	
	
	protected void Initialize()
	{
		try{
			RXQueue = "LightingRXQueue";
			TXQueue = "LightingQueue";
			bStatus = false;
			nColourTemp = 100;
			nBrightness= 100;
			ComponentMessageListener oRef = (ComponentMessageListener) ComponentMessageListener.getInstance();
			oRef.init(RXQueue);
			sendMessage("Connect",TXQueue);
			//Set current temperature to 20 by default. 
			sendMessage("Brightness:"+Integer.toString(nBrightness),TXQueue);
			sendMessage("OperationalStatus:"+Boolean.toString(bStatus),TXQueue);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		// Get initial status and desired temp
	}


	public Boolean getbStatus() {
		return bStatus;
	}

	public void setbStatus(Boolean bStatus) {
		try{
			this.bStatus = bStatus;
			sendMessage("OperationalStatus:"+Boolean.toString(bStatus), TXQueue);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}

	public int getnColourTemp() {
		return nColourTemp;
	}
	
	public void setnColourTemp(int nColourTemp) {
		try{
			this.nColourTemp = nColourTemp;
			sendMessage("ColourTemperature:"+Integer.toString(nColourTemp), TXQueue);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}

	public int getnBrightness() {
		return nBrightness;
	}

	public void setnBrightness(int nBrightness) {
		try{
			this.nBrightness = nBrightness;
			sendMessage("Brightness:"+Integer.toString(nBrightness), TXQueue);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}

	@Override
	protected void MessageHandler(String msg) throws Exception
	{
		if (msg.contains("OperationalStatus")){
			setbStatus(Boolean.parseBoolean(msg.split(":")[1]));
		}
		else if(msg.contains("Brightness")){
			setnBrightness(Integer.parseInt(msg.split(":")[1]));
		}
		else if(msg.contains("ColourTemperature")){
			setnColourTemp(Integer.parseInt(msg.split(":")[1]));
		}
	}
}
