package com.hardcoresoft.has.components.security;

import java.util.*;

import com.hardcoresoft.has.components.HASComponent;
import com.hardcoresoft.has.exceptions.NameConflictException;

public class SecurityComponent extends HASComponent implements ISecurityComponent
{

	SecurityStatus status = SecurityStatus.Disarmed;
	String password = "";
	HashMap<String, Sensor> sensors = new HashMap<String, Sensor>();
	HashMap<String, Alarm> alarms = new HashMap<String, Alarm>();

	@Override
	protected void MessageHandler(String msg) throws Exception
	{
		if (msg.startsWith("getStatus"))
		{
			sendMessage(msg + ":" + getStatus().toString());
		}
		else if (msg.startsWith("arm"))
		{
			sendMessage(msg + ":" + arm());
		}
		else if (msg.startsWith("disarm"))
		{
			try
			{
				String pass = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
				sendMessage(msg + ":" + disarm(pass));
			}
			catch (Exception ex)
			{
				LogException(ex);
				LogException(new Exception(msg + ":Does not contain a valid password parameter"));
				sendMessage(msg + ":Does not contain a valid password parameter");
			}
		}
		else if (msg.startsWith("updateCode"))
		{
			try
			{
				String oldCode = msg.substring(msg.indexOf("(") + 1, msg.indexOf(",")).trim();
				String newCode = msg.substring(msg.indexOf(",") + 1, msg.indexOf(")")).trim();
				sendMessage(msg + ":" + updateCode(oldCode, newCode));
			}
			catch (Exception ex)
			{
				LogException(ex);
				LogException(new Exception(msg + ":Does not contain valid password parameters"));
				sendMessage(msg + ":Does not contain valid password parameters");
			}
		}
		else if (msg.startsWith("activateAlarm"))
		{
			sendMessage(msg + ":" + activateAlarm());
		}
		else if (msg.startsWith("deactivateAlarm"))
		{
			sendMessage(msg + ":" + deactivateAlarm());
		}
		else if (msg.startsWith("callEmergencyServices"))
		{
			sendMessage(msg + ":" + callEmergencyServices());
		}
		else if (msg.startsWith("addSensor"))
		{
			try
			{
				String name = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
				addSensor(name);
				sendMessage(msg + ":" + true);
			}
			catch (Exception ex)
			{
				LogException(ex);
				LogException(new Exception(msg + ":Does not contain a valid name parameter"));
				sendMessage(msg + ":Does not contain a valid name parameter");
			}
		}
		else if (msg.startsWith("removeSensor"))
		{
			try
			{
				String name = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
				sendMessage(msg + ":" + removeSensor(name));
			}
			catch (Exception ex)
			{
				LogException(ex);
				LogException(new Exception(msg + ":Does not contain a valid name parameter"));
				sendMessage(msg + ":Does not contain a valid name parameter");
			}
		}
		else if (msg.startsWith("addAlarm"))
		{
			try
			{
				String name = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
				addAlarm(name);
				sendMessage(msg + ":" + true);
			}
			catch (Exception ex)
			{
				LogException(ex);
				LogException(new Exception(msg + ":Does not contain a valid name parameter"));
				sendMessage(msg + ":Does not contain a valid name parameter");
			}
		}
		else if (msg.startsWith("removeAlarm"))
		{
			try
			{
				String name = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
				sendMessage(msg + ":" + removeAlarm(name));
			}
			catch (Exception ex)
			{
				LogException(ex);
				LogException(new Exception(msg + ":Does not contain a valid name parameter"));
				sendMessage(msg + ":Does not contain a valid name parameter");
			}
		}
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
		}
		else
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
		}
		else
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
			}
			catch (Exception ex)
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
			}
			catch (Exception ex)
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
