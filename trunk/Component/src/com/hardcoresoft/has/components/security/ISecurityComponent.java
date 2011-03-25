package com.hardcoresoft.has.components.security;

import com.hardcoresoft.has.exceptions.NameConflictException;

public interface ISecurityComponent
{

	public abstract SecurityStatus getStatus();

	public abstract boolean arm();

	public abstract boolean disarm(String pass);

	public abstract boolean updateCode(String oldCode, String newCode);

	public abstract boolean activateAlarm();

	public abstract boolean deactivateAlarm();

	public abstract boolean callEmergencyServices();

	public abstract void addSensor(String name) throws NameConflictException;

	public abstract boolean removeSensor(String name);

	public abstract void addAlarm(String name) throws NameConflictException;

	public abstract boolean removeAlarm(String name);

}