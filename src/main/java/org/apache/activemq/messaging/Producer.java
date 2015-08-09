package org.apache.activemq.messaging;
 
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
 
public class Producer
{
    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
 
    public Producer(ConnectionFactory factory, String queueName) throws JMSException
    {
        this.factory = factory;
        String user = "";
        String password = "";
        this.connection = this.factory.createConnection(user, password);
        System.out.println("Starting connection to ActiveMQ.");
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueName);

        System.out.println("Destination Queue created.");
        producer = session.createProducer(destination);
    }
 
    public void run() throws JMSException
    {
        for (int i = 0; i < 100; i++)
        {
            Message message = session.createTextMessage(String.format("Message --> %d <-- produced.", i));
            System.out.print("Message " + i + " ");
            producer.send(message);
            System.out.println(message.getJMSMessageID());
        }
    }
 
    public void close() throws JMSException
    {
        if (connection != null)
        {
            connection.close();
        }
    }
 
}
