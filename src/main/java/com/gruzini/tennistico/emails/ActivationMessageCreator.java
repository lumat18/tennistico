package com.gruzini.tennistico.emails;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component("activation")
public class ActivationMessageCreator implements MessageCreator {

    private static final String SUBJECT = "Welcome to Tennistico!";
    private static final String TEXT = "In order to activate you account, please click the link below: \n";

    @Override
    public SimpleMailMessage create(String email, String tokenValue) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(SUBJECT);
        message.setTo(email);
        message.setText(TEXT + createActivationLink(tokenValue));
        return message;
    }

    //TODO change link to real request address
    private String createActivationLink(final String tokenValue) {
        return "http://localhost:8080/activate?value=" + tokenValue;
    }
}
