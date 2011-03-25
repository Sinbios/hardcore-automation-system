package com.hardcoresoft.has;
import java.util.*; 
import com.hardcoresoft.has.exceptions.*;

public class LightingComponent extends HASComponent {

	ArrayList<Light> lights = new ArrayList<Light>();
	
	@Override
	protected void MessageHandler(String msg) throws Exception {
		// TODO: Parse the message and do something with it
	}
	
	public boolean turnLightsOn()
	{
		boolean success = true;
		for(Light light : lights)
		{
			try
			{
				light.turnLightOn();
			}
			catch(Exception ex)
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
		for(Light light : lights)
		{
			try
			{
				light.turnLightOff();
			}
			catch(Exception ex)
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
		for(Light light : lights)
		{
			try
			{
				light.setBrightness(brightness);
			}
			catch(Exception ex)
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
		for(Light light : lights)
		{
			try
			{
				light.setTemperature(temp);
			}
			catch(Exception ex)
			{
				success = false;
				LogException(ex);
			}			
		}
		return success;
	}
	
	public void addLight(String name) throws NameConflictException
	{
		for(Light light : lights)
		{
			if(light.name == name)
				throw new NameConflictException(name);
		}
		lights.add(new Light(name,false,0,0));
	}
	
	public boolean removeLight(String name) throws NotFoundException
	{
		for(Light light : lights)
		{
			if(light.name == name)
			{
				lights.remove(light);
				return true;
			}
		}
		throw new NotFoundException(name);
	}
}
