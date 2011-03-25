package com.hardcoresoft.has.components.hvac;

public class VentilationUnit extends HVACUnit
{
	int fanSpeed = 0;

	public int getFanSpeed()
	{
		return fanSpeed;
	}

	public boolean setFanSpeed(int speed) throws Exception
	{
		fanSpeed = speed;
		return true;
	}
}
