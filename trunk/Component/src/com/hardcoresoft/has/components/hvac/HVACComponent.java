package com.hardcoresoft.has.components.hvac;

import java.util.HashMap;

import com.hardcoresoft.has.components.HASComponent;
import com.hardcoresoft.has.exceptions.NameConflictException;

public class HVACComponent extends HASComponent implements IHVACComponent
{

	@Override
	protected void MessageHandler(String msg) throws Exception
	{
		if (msg.startsWith("getStatus"))
		{
			sendMessage(msg + ":" + getStatus().toString());
		}
		else if (msg.startsWith("setDesiredTemperature"))
		{
			try
			{
				int temp = Integer.parseInt(msg.substring(msg.indexOf("(") + 1, msg.indexOf(")")));
				sendMessage(msg + ":" + setDesiredTemperature(temp));
			}
			catch (Exception ex)
			{
				LogException(new Exception(msg + ":Does not contain a valid temperature parameter"));
				sendMessage(msg + ":Does not contain a valid temperature parameter");
			}
		}
		else if (msg.startsWith("isHeating"))
		{
			sendMessage(msg + ":" + isHeating());
		}
		else if (msg.startsWith("isAC"))
		{
			sendMessage(msg + ":" + isAC());
		}
		else if (msg.startsWith("isVentilating"))
		{
			sendMessage(msg + ":" + isVentilating());
		}
		else if (msg.startsWith("activateVentilation"))
		{
			try
			{
				int fanSpeed = Integer.parseInt(msg.substring(msg.indexOf("(") + 1, msg.indexOf(")")));
				sendMessage(msg + ":" + activateVentilation(fanSpeed));
			}
			catch (Exception ex)
			{
				LogException(new Exception(msg + ":Does not contain a valid fan speed parameter"));
				sendMessage(msg + ":Does not contain a valid fan speed parameter");
			}
		}
		else if (msg.startsWith("deactivateVentilation"))
		{
			sendMessage(msg + ":" + deactivateVentilation());
		}
		else if (msg.startsWith("addHeatingUnit"))
		{
			try
			{
				String name = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
				addHeatingUnit(name);
				sendMessage(msg + ":" + true);
			}
			catch (Exception ex)
			{
				LogException(ex);
				LogException(new Exception(msg + ":Does not contain a valid name parameter"));
				sendMessage(msg + ":Does not contain a valid name parameter");
			}
		}
		else if (msg.startsWith("addACUnit"))
		{
			try
			{
				String name = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
				addACUnit(name);
				sendMessage(msg + ":" + true);
			}
			catch (Exception ex)
			{
				LogException(ex);
				LogException(new Exception(msg + ":Does not contain a valid name parameter"));
				sendMessage(msg + ":Does not contain a valid name parameter");
			}
		}
		else if (msg.startsWith("addVentilationUnit"))
		{
			try
			{
				String name = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
				addVentilationUnit(name);
				sendMessage(msg + ":" + true);
			}
			catch (Exception ex)
			{
				LogException(ex);
				LogException(new Exception(msg + ":Does not contain a valid name parameter"));
				sendMessage(msg + ":Does not contain a valid name parameter");
			}
		}
		else if (msg.startsWith("removeHeatingUnit"))
		{
			try
			{
				String name = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
				sendMessage(msg + ":" + removeHeatingUnit(name));
			}
			catch (Exception ex)
			{
				LogException(ex);
				LogException(new Exception(msg + ":Does not contain a valid name parameter"));
				sendMessage(msg + ":Does not contain a valid name parameter");
			}
		}
		else if (msg.startsWith("removeACUnit"))
		{
			try
			{
				String name = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
				sendMessage(msg + ":" + removeACUnit(name));
			}
			catch (Exception ex)
			{
				LogException(ex);
				LogException(new Exception(msg + ":Does not contain a valid name parameter"));
				sendMessage(msg + ":Does not contain a valid name parameter");
			}
		}
		else if (msg.startsWith("removeVentilationUnit"))
		{
			try
			{
				String name = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
				sendMessage(msg + ":" + removeVentilationUnit(name));
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
		subject = "HVACQueue";
		
		// Get initial status and desired temp
	}

	HVACStatus status = HVACStatus.Inactive;
	int desiredTemperature = 20;

	HashMap<String, TemperatureSensor> sensors = new HashMap<String, TemperatureSensor>();
	HashMap<String, ACUnit> acUnits = new HashMap<String, ACUnit>();
	HashMap<String, HeatingUnit> heatingUnits = new HashMap<String, HeatingUnit>();
	HashMap<String, VentilationUnit> ventilationUnits = new HashMap<String, VentilationUnit>();

	public HVACStatus getStatus()
	{
		return status;
	}

	public boolean setDesiredTemperature(int temp)
	{
		desiredTemperature = temp;
		return true;
	}

	public boolean isHeating()
	{
		for (HeatingUnit heat : heatingUnits.values())
		{
			if (heat.getHeatingRate() > 0)
				return true;
		}
		return false;
	}

	public boolean isAC()
	{
		for (ACUnit ac : acUnits.values())
		{
			if (ac.getCoolingRate() > 0)
				return true;
		}
		return false;
	}

	public boolean isVentilating()
	{
		for (VentilationUnit vent : ventilationUnits.values())
		{
			if (vent.getFanSpeed() > 0)
				return true;
		}
		return false;
	}

	public boolean activateVentilation(int fanSpeed)
	{
		boolean success = true;
		for (VentilationUnit vent : ventilationUnits.values())
		{
			try
			{
				vent.setFanSpeed(fanSpeed);
			}
			catch (Exception ex)
			{
				LogException(ex);
				success = false;
			}
		}
		return success;
	}

	public boolean deactivateVentilation()
	{
		boolean success = true;
		for (VentilationUnit vent : ventilationUnits.values())
		{
			try
			{
				vent.setFanSpeed(0);
			}
			catch (Exception ex)
			{
				LogException(ex);
				success = false;
			}
		}
		return success;
	}

	public void addHeatingUnit(String name) throws NameConflictException
	{
		if (heatingUnits.containsKey(name))
		{
			throw new NameConflictException(name);
		}
		heatingUnits.put(name, new HeatingUnit());
	}

	public boolean removeHeatingUnit(String name)
	{
		return (heatingUnits.remove(name) == null);
	}

	public void addACUnit(String name) throws NameConflictException
	{
		if (acUnits.containsKey(name))
		{
			throw new NameConflictException(name);
		}
		acUnits.put(name, new ACUnit());
	}

	public boolean removeACUnit(String name)
	{
		return (acUnits.remove(name) == null);
	}

	public void addVentilationUnit(String name) throws NameConflictException
	{
		if (ventilationUnits.containsKey(name))
		{
			throw new NameConflictException(name);
		}
		ventilationUnits.put(name, new VentilationUnit());
	}

	public boolean removeVentilationUnit(String name)
	{
		return (ventilationUnits.remove(name) == null);
	}
}
