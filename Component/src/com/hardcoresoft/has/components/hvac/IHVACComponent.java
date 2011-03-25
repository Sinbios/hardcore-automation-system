package com.hardcoresoft.has.components.hvac;

import com.hardcoresoft.has.exceptions.NameConflictException;

public interface IHVACComponent
{

	public abstract HVACStatus getStatus();

	public abstract boolean setDesiredTemperature(int temp);

	public abstract boolean isHeating();

	public abstract boolean isAC();

	public abstract boolean isVentilating();

	public abstract boolean activateVentilation(int fanSpeed);

	public abstract boolean deactivateVentilation();

	public abstract void addHeatingUnit(String name)
			throws NameConflictException;

	public abstract boolean removeHeatingUnit(String name);

	public abstract void addACUnit(String name) throws NameConflictException;

	public abstract boolean removeACUnit(String name);

	public abstract void addVentilationUnit(String name)
			throws NameConflictException;

	public abstract boolean removeVentilationUnit(String name);

}