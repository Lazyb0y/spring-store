package com.codewithmosh.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailNotificationService implements NotificationService {
    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private String port;

    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }

    @Override
    public void send(String message, String recipient) {
        System.out.println("Email address: " + host + ":" + port);
        System.out.println("Sending email to " + recipient + ", Message: " + message);
    }
}
