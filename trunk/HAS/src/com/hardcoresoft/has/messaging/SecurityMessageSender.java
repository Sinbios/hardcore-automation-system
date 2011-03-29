package com.hardcoresoft.has.messaging;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.hardcoresoft.has.datastorage.DataStorage;
import com.hardcoresoft.has.datastorage.SecurityMode;

public class SecurityMessageSender {
	
	protected static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	protected static String subject = DataStorage.getInstance().getoSecurityData().getoSecurityData().getsMsgQueueName();
	
	private static SecurityMessageSender handler = new SecurityMessageSender();
	
	private SecurityMessageSender() {
		
	}
	
	public static SecurityMessageSender getInstance() {
		return handler;
	}
	
	
	/**
	 * Function: public void sendMessage
	 * Parameters: string body - message content to send.
	 * Description: Sends a string message to the Security component.
	 */
	public void sendMessage(String body) throws JMSException {
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
        Destination destination = session.createQueue(subject);

        // MessageProducer is used for sending messages (as opposed
        // to MessageConsumer which is used for receiving them)
        MessageProducer producer = session.createProducer(destination);

        // We will send a small text message saying 'Hello' in Japanese
        TextMessage message = session.createTextMessage(body);

        // Here we are sending the message!
        producer.send(message);
        System.out.println("Sent message to Security: '" + message.getText() + "'");

        connection.close();
	}
	
	/**
	 * Function: public void sendNewPin(int pin)
	 * Parameters: int pin - new pin value to send.
	 * Description: Sends a new pin message to the security component.
	 */
	public void sendNewPin(int pin){
		try{
			sendMessage("NewPin:"+Integer.toString(pin));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: public void disarmPin (int pin){
	 * Parameters: int pin - pin attempt to send. 
	 * Description: Sends an entered pin to the component.
	 */
	public void disarmPin (int pin){
		try{
			sendMessage("DisarmPin:"+Integer.toString(pin));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: public void sendStatus(SecurityMode mode)
	 * Parameters: SecurityMode mode - mode value to send.
	 * Description: Sends a new SecurityMode to the security component. 
	 */
	public void sendStatus(SecurityMode mode){
		try{
			sendMessage("Status:"+ mode.ordinal());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
