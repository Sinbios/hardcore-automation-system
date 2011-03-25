package com.hardcoresoft.has.components.hvac;

import java.util.HashMap;

import com.hardcoresoft.has.components.HASComponent;
import com.hardcoresoft.has.exceptions.NameConflictException;

public class HVACComponent extends HASComponent implements IHVACComponent
{

	@Override
	protected void MessageHandler(String msg) throws Exception
	{
		// TODO: Parse the message and do something with it
	}

	protected void Initialize()
	{
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
			} catch (Exception ex)
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
			} catch (Exception ex)
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
