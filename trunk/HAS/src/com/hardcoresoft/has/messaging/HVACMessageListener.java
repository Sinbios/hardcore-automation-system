package com.hardcoresoft.has.messaging;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.hardcoresoft.has.datastorage.DataStorage;
import com.hardcoresoft.has.datastorage.HVACDataController;

import com.hardcoresoft.has.datastorage.HVACStatus;
import com.hardcoresoft.has.util.MessageUtil;


import javax.jms.*;

public class HVACMessageListener implements MessageListener {
	private static HVACMessageListener listener = new HVACMessageListener();
    private static int ackMode = Session.AUTO_ACKNOWLEDGE;
    private static String messageQueueName = MessageUtil.getQueueName();

    private Session session;
    private boolean transacted = false;
    private MessageProducer replyProducer;
    
    public HVACMessageListener() {
        this.setupMessageQueueConsumer("HVACQueue");
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
                messageHandler(messageText);
                   
            }
            
    
            //Set the correlation ID from the received message to be the correlation id of the response message
            //this lets the client identify which message this is a response to if it has more than
            //one outstanding message to the server
            response.setJMSCorrelationID(message.getJMSCorrelationID());

            //Send the response to the Destination specified by the JMSReplyTo field of the received message,
            //this is presumably a temporary queue created by the client
            
            System.out.println("Server received message. MessageHandler would do work here.");
            // Commenting this out for now since I don't want to reply.
            //this.replyProducer.send(message.getJMSReplyTo(), response);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    private void messageHandler(String message)
    {
    	try
    	{
	    	if(message.equals("Connect")){
	    		DataStorage.getInstance().getoHVACData().getoHVACData().setbConnected(true);
	    		DataStorage.getInstance().updateHVACData();
	    	}
	    	else if(message.contains("CurrentTemperature")){
	    		DataStorage.getInstance().getoHVACData().getoHVACData().setnCurrentTemperature(Double.parseDouble(message.split(":")[1]));
	    		DataStorage.getInstance().updateHVACData();
	    	}
	    	else if(message.contains("Status")){
	    		DataStorage.getInstance().getoHVACData();
				HVACStatus st = HVACDataController.convertIntToHVACStatus(Integer.parseInt((message.split(":")[1])));
	    		DataStorage.getInstance().getoHVACData().getoHVACData().setoStatus(st);
	    		DataStorage.getInstance().updateHVACData();
	    	}
	    	else if(message.contains("DesiredTemperature")){
	    		DataStorage.getInstance().getoHVACData().getoHVACData().setnDesiredTemperature(Double.parseDouble(message.split(":")[1]));
	    		DataStorage.getInstance().updateHVACData();
	    	}
	    	else if(message.contains("Disconnect"))
	    	{
	    		DataStorage.getInstance().getoHVACData().getoHVACData().setbConnected(false);
	    		DataStorage.getInstance().updateHVACData();
	    	}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	
    }
    
    public static MessageListener getInstance() {
    	return listener;
    }
}
