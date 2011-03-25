package com.hardcoresoft.has.exceptions;

public class NameConflictException extends Exception {
	public NameConflictException(String name) {
		super("The name \"" + name + "\" already exists in the system.");
	}
}
