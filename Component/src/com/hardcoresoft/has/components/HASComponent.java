package com.hardcoresoft.has.components;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.*;
import org.apache.activemq.util.JMSExceptionSupport;

import com.hardcoresoft.has.components.hvac.HVACComponent;

public abstract class HASComponent
{

	// Defined ourselves or provided by ActiveMQ?
	// private static MessageListener messageListener;
	// private static MessageSender messageSender;

	// Shared component data fields
	String ipAddress;
	int port;
	boolean connected;
	protected static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	protected static String RXQueue = "TESTQUEUE";
	protected static String TXQueue = "TESTQUEUE";

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
	
	public void handle(String action, String[] parameters) {
		if (action.equals("Sendmessage")) {
			if (parameters[1] != null) {
				try {
					if(parameters[2] != null ){
						this.sendMessage(parameters[1]+":"+parameters[2],TXQueue);
						if(TXQueue.equals("HVACQueue") && parameters[1].contains("Temperature"))
						{
							this.sendMessage(parameters[1]+":"+parameters[2],RXQueue);
							
						}
					}
				}catch(Exception e)
				{
					try{
						this.sendMessage(parameters[1],TXQueue);
					}
					catch(Exception ex){
						ex.printStackTrace();
				}
				
				}
			}
		} else if (action.equals("Status")) {
			if (this.getClass().getName().equals(HVACComponent.class.getName())) {
				System.out.println("Current Temperature: " + ((HVACComponent)this).getCurrentTemperature());
				System.out.println("Desired Temperature: " + ((HVACComponent)this).getDesiredTemperature());
				System.out.println("Status: " + ((HVACComponent)this).getStatus());
			}
		}
	}
	
	protected void sendMessage(String body,String destQueue) throws JMSException {
		ConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // JMS messages are sent and received using a Session. We will
        // create here a non-transactional session object. If you want
        // to use transactions you should set the first parameter to 'true'
        Session session = connection.createSession(false,
            Session.AUTO_ACKNOWLEDGE);

        // Destination represents here our queue 'TESTQUEUE' on the
        // JMS server. You don't have to do anything special on the
        // server to create it, it will be created automatically.
        Destination destination = session.createQueue(destQueue);

        // MessageProducer is used for sending messages (as opposed
        // to MessageConsumer which is used for receiving them)
        MessageProducer producer = session.createProducer(destination);

        // We will send a small text message saying 'Hello' in Japanese
        TextMessage message = session.createTextMessage(body);

        // Here we are sending the message!
        producer.send(message);
        System.out.println("Sent message '" + message.getText() + "'");

        connection.close();
	}
	
}
