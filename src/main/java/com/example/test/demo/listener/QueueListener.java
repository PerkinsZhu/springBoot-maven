package com.example.test.demo.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {

    @JmsListener(destination = "publish.queue", containerFactory = "jmsListenerContainerQueue")
    @SendTo("out.queue")  // 把返回值重新发送到这个out.queue,这样out.queue的监听器就可以接受到消息了
    public String receive(String text) {
        System.out.println("QueueListener: consumer-a 收到一条信息: " + text);
        return "consumer-a received : " + text;
    }

    //支持多个listener  ，每个queue只能被其中一个listener接受到
    @JmsListener(destination = "publish.queue", containerFactory = "jmsListenerContainerQueue")
    public void receive2(String text) {
        System.out.println("QueueListener2: consumer-a 收到一条信息: " + text);
    }

    @JmsListener(destination = "publish.queue", containerFactory = "jmsListenerContainerQueue")
    @SendTo("out.queue")  // 把返回值重新发送到这个out.queue,这样out.queue的监听器就可以接受到消息了
    public String receive3(String text) {
        System.out.println("QueueListener3: consumer-a 收到一条信息: " + text);
        return "consumer3-a received : " + text;
    }
}