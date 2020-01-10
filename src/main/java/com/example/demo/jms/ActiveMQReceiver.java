package com.example.demo.jms;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@EnableJms
public class ActiveMQReceiver {

    @JmsListener(destination = "ExceptionQueue")
    public void HandleExceptionQueueMessage(String customizedExceptionDetails) {
        System.out.println(customizedExceptionDetails);
    }

}
