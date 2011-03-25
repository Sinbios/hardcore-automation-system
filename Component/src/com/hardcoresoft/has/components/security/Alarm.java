package com.hardcoresoft.has.components.security;

public class Alarm
{
	boolean active = false;

	public boolean isActive()
	{
		return active;
	}

	public boolean activate() throws Exception
	{
		active = true;
		return true;
	}

	public boolean deactivate() throws Exception
	{
		active = false;
		return true;
	}
}
