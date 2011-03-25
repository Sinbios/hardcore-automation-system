package com.hardcoresoft.has;

/*
 * This is the main program for a subsystem component
 */
public class StartComponent {

	static HASComponent component;
	/**
	 * args[0] specifies the type of the subsystem
	 */
	public static void main(String[] args) {
		if (args.length != 1)
		{
			// TODO: keep usage output updated
			System.out.println("Usage: hascomponent <type>, where type is lighting, security, or hvac");
			return;
		}
		// Initialize component type
		if(args[0].toLowerCase() == "lighting")
		{
			component = new LightingComponent();
		}
		else if(args[0].toLowerCase() == "security")
		{
			component = new SecurityComponent();
		}
		else if(args[0].toLowerCase() == "hvac")
		{
			component = new HVACComponent();
		}
		else
		{
			System.out.println("Please specify a valid component type");
			return;
		}
		
	}

}
