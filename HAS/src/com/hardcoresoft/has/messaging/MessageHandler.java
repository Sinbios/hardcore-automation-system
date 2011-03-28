package com.hardcoresoft.has.messaging;

public class MessageHandler {
	
	private static MessageHandler handler = new MessageHandler();
	
	private MessageHandler() {
		
	}
	
	public static MessageHandler getInstance() {
		return handler;
	}
	

}
