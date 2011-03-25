package com.hardcoresoft.has.components;

public abstract class HASComponent
{

	// Defined ourselves or provided by ActiveMQ?
	// private static MessageListener messageListener;
	// private static MessageSender messageSender;

	// Shared component data fields
	String ipAddress;
	int port;
	boolean connected;

	public HASComponent()
	{
		// Call the subclass init routine
		Initialize();
		// TODO: Initialize a message listener and listen for incoming messages,
		// calling MessageHandler when a message is received
	}

	// Message handler implemented by specific component
	protected abstract void MessageHandler(String msg) throws Exception;

	protected abstract void Initialize();

	protected void LogException(Exception ex)
	{
		// Log an exception here
	}
}
