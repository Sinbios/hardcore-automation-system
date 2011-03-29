package com.hardcoresoft.has.components.security;

import java.util.*;

import com.hardcoresoft.has.components.ComponentMessageListener;
import com.hardcoresoft.has.components.HASComponent;
import com.hardcoresoft.has.exceptions.NameConflictException;

public class SecurityComponent extends HASComponent
{
	int nStatus = 0;
	int nPin;
	
	protected void Initialize()
	{
		try{
			RXQueue = "SecurityRXQueue";
			TXQueue = "SecurityQueue";
			nStatus = 0;
			ComponentMessageListener oRef = (ComponentMessageListener) ComponentMessageListener.getInstance();
			oRef.init(RXQueue);
			sendMessage("Connect",TXQueue);
			//Set current temperature to 20 by default. 
			sendMessage("Status:"+Integer.toString(nStatus),TXQueue);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		// Get initial status and desired temp
	}

	public int getnStatus() {
		return nStatus;
	}

	public void setnStatus(int nStatus) {
		try{
			this.nStatus = nStatus;
			sendMessage("Status:"+Integer.toString(nStatus), TXQueue);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}

	public int getnPin() {
		return nPin;
	}

	public void setnPin(int nPin) {	
		try{
			this.nPin = nPin;
			sendMessage("NewPin:"+Integer.toString(nPin), TXQueue);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public void disArm(int nPinAttempt){
		if(nPinAttempt == nPin){
			setnStatus(0);
		}
	}

	@Override
	protected void MessageHandler(String msg) throws Exception
	{
		if(msg.contains("Status")){
			setnStatus(Integer.parseInt(msg.split(":")[1]));
		}
		else if(msg.contains("DisarmPin")){
			disArm(Integer.parseInt(msg.split(":")[1]));
		}
		else if(msg.contains("NewPin")){
			setnPin(Integer.parseInt(msg.split(":")[1]));
		}
	}
}
