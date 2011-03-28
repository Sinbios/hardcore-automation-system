package com.hardcoresoft.has.components.lighting;

import java.util.*;
import com.hardcoresoft.has.components.HASComponent;
import com.hardcoresoft.has.exceptions.*;

public class LightingComponent extends HASComponent implements ILightingComponent
{

	HashMap<String, Light> lights = new HashMap<String, Light>();

	@Override
	protected void MessageHandler(String msg) throws Exception
	{
		if (msg.startsWith("turnLightsOn"))
		{
			sendMessage(msg + ":" + turnLightsOn());
		}
		else if (msg.startsWith("turnLightsOff"))
		{
			sendMessage(msg + ":" + turnLightsOff());
		}
		else if (msg.startsWith("setBrightness"))
		{
			try
			{
				int brightness = Integer.parseInt(msg.substring(msg.indexOf("(") + 1, msg.indexOf(")")));
				sendMessage(msg + ":" + setBrightness(brightness));
			}
			catch (Exception ex)
			{
				LogException(new Exception(msg + ":Does not contain a valid brightness parameter"));
				sendMessage(msg + ":Does not contain a valid brightness parameter");
			}
		}
		else if (msg.startsWith("setTemperature"))
		{
			try
			{
				int temp = Integer.parseInt(msg.substring(msg.indexOf("(") + 1, msg.indexOf(")")));
				sendMessage(msg + ":" + setTemperature(temp));
			}
			catch (Exception ex)
			{
				LogException(new Exception(msg + ":Does not contain a valid temperature parameter"));
				sendMessage(msg + ":Does not contain a valid temperature parameter");
			}
		}
		else if (msg.startsWith("addLight"))
		{
			try
			{
				String name = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
				addLight(name);
				sendMessage(msg + ":" + true);
			}
			catch (Exception ex)
			{
				LogException(ex);
				LogException(new Exception(msg + ":Does not contain a valid name parameter"));
				sendMessage(msg + ":Does not contain a valid name parameter");
			}
		}
		else if (msg.startsWith("removeLight"))
		{
			try
			{
				String name = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
				sendMessage(msg + ":" + removeLight(name));
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

	}

	public boolean turnLightsOn()
	{
		boolean success = true;
		for (Light light : lights.values())
		{
			try
			{
				light.turnLightOn();
			}
			catch (Exception ex)
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
			}
			catch (Exception ex)
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
			}
			catch (Exception ex)
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
			}
			catch (Exception ex)
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
