package com.hardcoresoft.has.servlets;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Servlet implementation class TestMessageServlet
 */
@WebServlet(description = "Using this to test out sending JMS messaging from server to Java app.", urlPatterns = { "/TestMessageServlet" })
public class TestMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // Name of the queue we will receive messages from
    private static String subject = "TESTQUEUE";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionFactory connectionFactory
	        = new ActiveMQConnectionFactory(url);
	    Connection connection;
		try {
			connection = connectionFactory.createConnection();
		    connection.start();
		
		    // Creating session for seding messages
		    Session session = connection.createSession(false,
		        Session.AUTO_ACKNOWLEDGE);
		
		    // Getting the queue 'TESTQUEUE'
		    Destination destination = session.createQueue(subject);
		
		    // MessageConsumer is used for receiving (consuming) messages
		    MessageConsumer consumer = session.createConsumer(destination);
		
		    // Here we receive the message.
		    // By default this call is blocking, which means it will wait
		    // for a message to arrive on the queue.
		    Message message = consumer.receive();
		
		    // There are many types of Message and TextMessage
		    // is just one of them. Producer sent us a TextMessage
		    // so we must cast to it to get access to its .getText()
		    // method.
		    if (message instanceof TextMessage) {
		        TextMessage textMessage = (TextMessage) message;
		        System.out.println("Received message '"
		            + textMessage.getText() + "'");
		    }
		    connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write("This is a response.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
