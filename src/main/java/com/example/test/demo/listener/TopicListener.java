package com.example.test.demo.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {

    @JmsListener(destination = "publish.topic", containerFactory = "jmsListenerContainerTopic")
    public void receive(String text) {
        System.out.println("TopicListener: consumer-a 收到一条信息: " + text);
    }

    //支持多个listener，每个topic，每个listener都可以接收到
    @JmsListener(destination = "publish.topic", containerFactory = "jmsListenerContainerTopic")
    public void receive2(String text) {
        System.out.println("TopicListener2: consumer-a 收到一条信息: " + text);
    }
}
