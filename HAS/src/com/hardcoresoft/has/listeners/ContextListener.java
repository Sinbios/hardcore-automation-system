package com.hardcoresoft.has.listeners;

import javax.servlet.ServletContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.hardcoresoft.has.messaging.HVACMessageListener;
import com.hardcoresoft.has.datastorage.DataStorage;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {
	
	  private ServletContext context = null;
	 

	  public ContextListener() {}

	  //This method is invoked when the Web Application
	  //has been removed and is no longer able to accept
	  //requests

	  public void contextDestroyed(ServletContextEvent event)
	  {

	    //Output a simple message to the server's console
	    System.out.println("Catalina says: CYA LATER MAH NIGGUH!");
	    this.context = null;

	  }


	  //This method is invoked when the Web Application
	  //is ready to service requests

	  public void contextInitialized(ServletContextEvent event)
	  {
		//Init data
		DataStorage.getInstance();
	    this.context = event.getServletContext();
	    HVACMessageListener.getInstance();
	    
	    
	    //Output a simple message to the server's console
	    System.out.println("Catalina says: SUP NIGGA, SERVER READY!");

	  }
}
