package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableJms
public class TestController {


    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    private static final Logger JMS_LOGGER = LoggerFactory.getLogger("JMS_LOGGER");

    private final JmsTemplate jmsTemplate;

    public TestController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping
    public String doSomething() {
        String messageToSendToQueue = "hellooo";
        JMS_LOGGER.info("heeej");
        jmsTemplate.convertAndSend("FireWolfQueue", messageToSendToQueue);
        return "Request is being processed";
    }

}
