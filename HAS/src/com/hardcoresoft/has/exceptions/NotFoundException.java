package com.hardcoresoft.has.exceptions;

public class NotFoundException extends Exception {
	public NotFoundException(String name) {
		super("The object named \"" + name + "\" could not be found.");
	}
}
