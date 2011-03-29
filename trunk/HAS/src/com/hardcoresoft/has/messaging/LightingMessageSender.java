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

import sun.jdbc.odbc.OdbcDef;

import com.hardcoresoft.has.datastorage.DataStorage;
import com.hardcoresoft.has.datastorage.HVACStatus;

public class LightingMessageSender {
	
	protected static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	protected static String subject = DataStorage.getInstance().getoLightingData().getoLightingData().getsMsgQueueName();
	
	private static LightingMessageSender handler = new LightingMessageSender();
	
	private LightingMessageSender() {
		
	}
	
	public static LightingMessageSender getInstance() {
		return handler;
	}
	
	
	/**
	 * Function: public void sendMessage
	 * Parameters: string body - message content to send.
	 * Description: Sends a string message to the Lighting component.
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
        System.out.println("Sent message to Lighting: '" + message.getText() + "'");

        connection.close();
	}
	
	/**
	 * Function: public void sendBrightness(int brightness)
	 * Parameters: int brightness - brightness value to send.
	 * Description: Sends a brightness message to the Lighting component.
	 */
	public void sendBrightness(int brightness){
		try{
			sendMessage("Brightness:"+Integer.toString(brightness));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: public void sendOperationalStatus(Boolean status)
	 * Parameters: Boolean status - operational status to send.
	 * Description: Sends a operational status message to the Lighting component. 
	 */
	public void sendOperationalStatus(Boolean status){
		try{
			sendMessage("OperationalStatus:"+Boolean.toString(status));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: public void sendColourTemp(int colourtemp)
	 * Parameters: int colourtemp - colourtemp value to send.
	 * Description: Sends a colour temp message to the Lighting component.
	 */
	public void sendColourTemp(int colourtemp){
		try{
			sendMessage("ColourTemperature:"+Integer.toString(colourtemp));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
