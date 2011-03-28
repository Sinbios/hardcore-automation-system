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

public class HVACMessageSender {
	
	protected static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	protected static String subject = DataStorage.getInstance().getoHVACData().getoHVACData().getsMsgQueueName();
	
	private static HVACMessageSender handler = new HVACMessageSender();
	
	private HVACMessageSender() {
		
	}
	
	public static HVACMessageSender getInstance() {
		return handler;
	}
	
	/**
	 * Function: public void sendMessage(
	 * Parameters: int value - integer to convert.
	 * Description: Converts an integer to an SecurityMode enum. 
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
        System.out.println("Sent message to HVAC: '" + message.getText() + "'");

        connection.close();
	}
	
}
