package com.hardcoresoft.has.components.lighting;

import com.hardcoresoft.has.exceptions.NameConflictException;

public interface ILightingComponent
{

	public abstract boolean turnLightsOn();

	public abstract boolean turnLightsOff();

	public abstract boolean setBrightness(int brightness);

	public abstract boolean setTemperature(int temp);

	public abstract void addLight(String name) throws NameConflictException;

	public abstract boolean removeLight(String name);

}