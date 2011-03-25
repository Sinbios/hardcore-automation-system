package com.hardcoresoft.has.components.hvac;

public class HVACUnit
{
	HVACStatus status = HVACStatus.Inactive;

	public HVACStatus getStatus()
	{
		return status;
	}

	public boolean activate() throws Exception
	{
		status = HVACStatus.Active;
		return true;
	}

	public boolean deactivate() throws Exception
	{
		status = HVACStatus.Inactive;
		return true;
	}
}
