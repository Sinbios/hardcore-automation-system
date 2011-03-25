package com.hardcoresoft.has.components.hvac;

public class ACUnit extends HVACUnit
{
	int coolingRate = 0;

	public int getCoolingRate()
	{
		return coolingRate;
	}

	public boolean setCoolingRate(int rate) throws Exception
	{
		coolingRate = rate;
		return true;
	}
}
