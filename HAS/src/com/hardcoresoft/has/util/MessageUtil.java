package com.hardcoresoft.has.util;

import org.apache.activemq.ActiveMQConnection;

public class MessageUtil {
    // Name of the queue we will receive messages from
    private static String subject = "TESTQUEUE";

    public static String getQueueName() {
    	return subject;
    }
}
