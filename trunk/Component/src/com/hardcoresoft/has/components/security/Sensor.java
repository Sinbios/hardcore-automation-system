package com.hardcoresoft.has.components.security;

public class Sensor
{

	boolean triggered = false;

	public boolean isTriggered()
	{
		return triggered;
	}

	public void reset()
	{
		triggered = false;
	}

	public void trigger()
	{
		triggered = true;
	}
}
