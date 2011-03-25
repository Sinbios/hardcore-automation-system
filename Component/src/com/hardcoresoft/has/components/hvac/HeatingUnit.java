package com.hardcoresoft.has.components.hvac;

public class HeatingUnit extends HVACUnit
{
	int heatingRate = 0;

	public int getHeatingRate()
	{
		return heatingRate;
	}

	public boolean setHeatingRate(int rate) throws Exception
	{
		heatingRate = rate;
		return true;
	}
}
