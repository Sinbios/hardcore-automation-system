package com.hardcoresoft.has.components;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ComponentMessageListener  implements MessageListener {
	private static ComponentMessageListener listener = new ComponentMessageListener();
    private static int ackMode = Session.AUTO_ACKNOWLEDGE;
    private static String messageQueueName = "TESTQUEUE";

    private Session session;
    private boolean transacted = false;
    private MessageProducer replyProducer;
    
    public ComponentMessageListener() {
        
    }

    public void init(String queue){
    	this.setupMessageQueueConsumer(queue);
    }
    
    private void setupMessageQueueConsumer(String queuename) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            this.session = connection.createSession(this.transacted, Session.AUTO_ACKNOWLEDGE);
            Destination adminQueue = this.session.createQueue(queuename);

            //Setup a message producer to respond to messages from clients, we will get the destination
            //to send to from the JMSReplyTo header field from a Message
            this.replyProducer = this.session.createProducer(null);
            this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            //Set up a consumer to consume messages off of the admin queue
            MessageConsumer consumer = this.session.createConsumer(adminQueue);
            consumer.setMessageListener(this);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void onMessage(Message message) {
        try {
            TextMessage response = this.session.createTextMessage();
            if (message instanceof TextMessage) {
                TextMessage txtMsg = (TextMessage) message;
                String messageText = txtMsg.getText();
                System.out.println("MSG[" + messageText + "]");
                try{
                	StartComponent.component.MessageHandler(messageText);	
                }
                catch(Exception e){
                	e.printStackTrace();
                }
                
            }

            //Set the correlation ID from the received message to be the correlation id of the response message
            //this lets the client identify which message this is a response to if it has more than
            //one outstanding message to the server
            response.setJMSCorrelationID(message.getJMSCorrelationID());

            //Send the response to the Destination specified by the JMSReplyTo field of the received message,
            //this is presumably a temporary queue created by the client
            
            //System.out.println("Component received message. MessageHandler would do work here.");
            // Commenting this out for now since I don't want to reply.
            //this.replyProducer.send(message.getJMSReplyTo(), response);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public static MessageListener getInstance() {
    	return listener;
    }
}
