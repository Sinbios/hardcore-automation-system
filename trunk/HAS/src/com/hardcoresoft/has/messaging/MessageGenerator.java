package com.hardcoresoft.has.messaging;

/**
 * Function: public class MessageGenerator
 * Description: Class for generating messages. 
 */
public class MessageGenerator {

	private static MessageGenerator handler = new MessageGenerator();
	
	private MessageGenerator() {
		
	}
	
	public static MessageGenerator getInstance() {
		return handler;
	}
	
}
