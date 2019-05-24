package com.example.test.demo.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

import static java.lang.System.out;

public class TestActiveMQ {
    private String url = "tcp://localhost:61616";
    private String topicName = "test-topic";

    @Test
    public void testProducer() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage("HELLO TEST TOPIC");
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testConsumer() throws Exception {
        Connection connection = new ActiveMQConnectionFactory(url).createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(message -> out.println(message.toString()));
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
