package com.hardcoresoft.has.components.lighting;

import java.util.*;
import com.hardcoresoft.has.components.HASComponent;
import com.hardcoresoft.has.exceptions.*;

public class LightingComponent extends HASComponent implements
		ILightingComponent
{

	HashMap<String, Light> lights = new HashMap<String, Light>();

	@Override
	protected void MessageHandler(String msg) throws Exception
	{
		// TODO: Parse the message and do something with it
	}

	protected void Initialize()
	{

	}

	public boolean turnLightsOn()
	{
		boolean success = true;
		for (Light light : lights.values())
		{
			try
			{
				light.turnLightOn();
			} catch (Exception ex)
			{
				success = false;
				LogException(ex);
			}
		}
		return success;
	}

	public boolean turnLightsOff()
	{
		boolean success = true;
		for (Light light : lights.values())
		{
			try
			{
				light.turnLightOff();
			} catch (Exception ex)
			{
				success = false;
				LogException(ex);
			}
		}
		return success;
	}

	public boolean setBrightness(int brightness)
	{
		boolean success = true;
		for (Light light : lights.values())
		{
			try
			{
				light.setBrightness(brightness);
			} catch (Exception ex)
			{
				success = false;
				LogException(ex);
			}
		}
		return success;
	}

	public boolean setTemperature(int temp)
	{
		boolean success = true;
		for (Light light : lights.values())
		{
			try
			{
				light.setTemperature(temp);
			} catch (Exception ex)
			{
				success = false;
				LogException(ex);
			}
		}
		return success;
	}

	public void addLight(String name) throws NameConflictException
	{
		if (lights.containsKey(name))
		{
			throw new NameConflictException(name);
		}
		lights.put(name, new Light(false, 0, 0));
	}

	public boolean removeLight(String name)
	{
		return (lights.remove(name) == null);
	}
}
