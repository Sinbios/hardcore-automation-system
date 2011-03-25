package com.hardcoresoft.has;

public class Light {
	boolean operationStatus;
	int brightness;
	int colourTemp;
	String name;
	
	Light(String name, boolean status, int brightness, int temp)
	{
		this.name = name;
		this.operationStatus = status;
		this.brightness = brightness;
		this.colourTemp = temp;
	}
	
	public boolean turnLightOff() throws Exception
	{
		operationStatus = false;
		return true;
	}
	
	public boolean turnLightOn() throws Exception
	{
		operationStatus = true;
		return true;
	}
	
	public boolean setBrightness(int brightness) throws Exception
	{
		this.brightness = brightness;
		return true;
	}	
	
	public boolean setTemperature(int temp) throws Exception
	{
		this.colourTemp = temp;
		return true;
	}
}
