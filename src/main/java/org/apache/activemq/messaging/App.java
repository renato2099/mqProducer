package org.apache.activemq.messaging;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 */
public class App 
{
    public static String brokerURL = "tcp://localhost:61616";

    public static void main( String[] args ) throws javax.jms.JMSException
    {
        // setup the connection to ActiveMQ
        //ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
        ConnectionFactory factory = new ActiveMQConnectionFactory(args[0]);
        //Producer producer = new Producer(factory, "test");
        Producer producer = new Producer(factory, args[1]);
        System.out.println( "Running producer." );
        producer.run();
        System.out.println( "Closing producer." );
        producer.close();
    }
}
