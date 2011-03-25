package com.hardcoresoft.has;

public abstract class HASComponent {

	// Defined ourselves or provided by ActiveMQ?
	// private static MessageListener messageListener;
	// private static MessageSender messageSender;
	
	public HASComponent() {
		
		// TODO: Initialize a message listener and listen for incoming messages, calling MessageHandler when a message is received
	}
	
	// Message handler implemented by specific component
	protected abstract void MessageHandler(String msg) throws Exception;
	
	protected void LogException(Exception ex)
	{
		// Log an exception here
	}
}
