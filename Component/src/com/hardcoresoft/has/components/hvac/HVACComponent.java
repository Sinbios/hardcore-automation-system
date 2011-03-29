package com.hardcoresoft.has.components.hvac;

import com.hardcoresoft.has.components.ComponentMessageListener;
import com.hardcoresoft.has.components.HASComponent;

public class HVACComponent extends HASComponent
{
	int status = 0;
	double currentTemperature = 20;
	double desiredTemperature = 20;
	
	
	protected void Initialize()
	{
		try{
			RXQueue = "HVACRXQueue";
			TXQueue = "HVACQueue";
			status = 0;
			currentTemperature = 20;
			desiredTemperature = 20; 
			ComponentMessageListener oRef = (ComponentMessageListener) ComponentMessageListener.getInstance();
			oRef.init(RXQueue);
			sendMessage("Connect",TXQueue);
			//Set current temperature to 20 by default. 
			sendMessage("CurrentTemperature:"+Double.toString(currentTemperature),TXQueue);
			sendMessage("Status:0",TXQueue);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		// Get initial status and desired temp
	}

	

	public double getCurrentTemperature() {
		return currentTemperature;
	}



	public void setCurrentTemperature(double currentTemperature) {
		try{
			this.currentTemperature = currentTemperature;
			if(this.desiredTemperature > this.currentTemperature){
				sendMessage("Status:1", TXQueue);
			}
			else if(this.desiredTemperature < this.currentTemperature){
				sendMessage("Status:2", TXQueue);
			}
			else
			{
				sendMessage("Status:0", TXQueue);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public double getDesiredTemperature() {
		return desiredTemperature;
	}



	public void setDesiredTemperature(double desiredTemperature) {
		try{
			this.desiredTemperature = desiredTemperature;
			sendMessage("DesiredTemperature:"+Double.toString(desiredTemperature),TXQueue);
			if(this.desiredTemperature > this.currentTemperature){
				sendMessage("Status:1", TXQueue);
			}
			else if(this.desiredTemperature < this.currentTemperature){
				sendMessage("Status:2", TXQueue);
			}
			else
			{
				sendMessage("Status:0", TXQueue);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		try{
			this.status = status;
			sendMessage("Status:"+Integer.toString(status), TXQueue);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}



	@Override
	protected void MessageHandler(String msg) throws Exception
	{
		if (msg.contains("DesiredTemperature")){
			setDesiredTemperature(Double.parseDouble(msg.split(":")[1]));
		}
		else if(msg.contains("Status")){
			setStatus(Integer.parseInt(msg.split(":")[1]));
		}
		else if(msg.contains("CurrentTemperature")){
			setCurrentTemperature(Double.parseDouble(msg.split(":")[1]));
		}
	}

}
