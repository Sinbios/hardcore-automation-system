package com.hardcoresoft.has.components.security;

import java.util.*;

import com.hardcoresoft.has.components.HASComponent;
import com.hardcoresoft.has.exceptions.NameConflictException;

public class SecurityComponent extends HASComponent implements
		ISecurityComponent
{

	SecurityStatus status = SecurityStatus.Disarmed;
	String password = "";
	HashMap<String, Sensor> sensors = new HashMap<String, Sensor>();
	HashMap<String, Alarm> alarms = new HashMap<String, Alarm>();

	@Override
	protected void MessageHandler(String msg) throws Exception
	{
		// TODO: Parse the message and do something with it
	}

	protected void Initialize()
	{
		// Get password from config

	}

	public SecurityStatus getStatus()
	{
		return status;
	}

	public boolean arm()
	{
		status = SecurityStatus.Armed;
		return true;
	}

	public boolean disarm(String pass)
	{
		if (pass == password)
		{
			status = SecurityStatus.Disarmed;
			return true;
		} else
		{
			return false;
		}
	}

	public boolean updateCode(String oldCode, String newCode)
	{
		if (oldCode == password)
		{
			password = newCode;
			return true;
		} else
		{
			return false;
		}
	}

	public boolean activateAlarm()
	{
		boolean success = true;
		for (Alarm alarm : alarms.values())
		{
			try
			{
				alarm.activate();
			} catch (Exception ex)
			{
				success = false;
				LogException(ex);
			}
		}
		return success;
	}

	public boolean deactivateAlarm()
	{
		boolean success = true;
		for (Alarm alarm : alarms.values())
		{
			try
			{
				alarm.deactivate();
			} catch (Exception ex)
			{
				success = false;
				LogException(ex);
			}
		}
		return success;
	}

	public boolean callEmergencyServices()
	{
		boolean success = true;
		// ???
		return success;
	}

	public void addSensor(String name) throws NameConflictException
	{
		if (sensors.containsKey(name))
		{
			throw new NameConflictException(name);
		}
		sensors.put(name, new SecuritySensor());
	}

	public boolean removeSensor(String name)
	{
		return (sensors.remove(name) == null);
	}

	public void addAlarm(String name) throws NameConflictException
	{
		if (alarms.containsKey(name))
		{
			throw new NameConflictException(name);
		}
		alarms.put(name, new Alarm());
	}

	public boolean removeAlarm(String name)
	{
		return (alarms.remove(name) == null);
	}
}
