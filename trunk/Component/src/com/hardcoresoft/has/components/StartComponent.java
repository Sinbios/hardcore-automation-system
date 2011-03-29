package com.hardcoresoft.has.components;

import java.util.Scanner;

import com.hardcoresoft.has.components.hvac.HVACComponent;
import com.hardcoresoft.has.components.lighting.LightingComponent;
import com.hardcoresoft.has.components.security.SecurityComponent;

/*
 * This is the main program for a subsystem component
 */
public class StartComponent
{

	static HASComponent component;

	/**
	 * args[0] specifies the type of the subsystem
	 */
	public static void main(String[] args)
	{
		if (args.length != 2)
		{
			// TODO: keep usage output updated
			System.out
					.println("Usage: hascomponent <type>, where type is lighting, security, or hvac");
			return;
		}
		// Initialize component type
		if (args[1].equals("lighting"))
		{
			component = new LightingComponent();
		} else if (args[1].equals("security"))
		{
			component = new SecurityComponent();
		} else if (args[1].equals("hvac"))
		{
			component = new HVACComponent();
		} else
		{
			System.out.println("Please specify a valid component type");
			return;
		}
		Scanner readUserInput = new Scanner(System.in);  
		String command = null;
		boolean quit = false;
		while (quit == false) {
			command = readUserInput.nextLine();
			String[] parameters = null;
			if (command != null) {
				if (command.equals("exit")) break;
				parameters = command.split(":"); 
				command = parameters[0];
				component.handle(command, parameters);
			}
		}
	}
}
