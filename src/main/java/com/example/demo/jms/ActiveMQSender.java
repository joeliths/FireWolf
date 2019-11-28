package com.example.demo.jms;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableJms
public class ActiveMQSender {

    private final JmsTemplate jmsTemplate;

    public ActiveMQSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessageToExceptionQueue(Object message) {
        jmsTemplate.convertAndSend("ExceptionQueue", message);
    }

}
