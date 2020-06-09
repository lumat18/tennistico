package com.gruzini.tennistico.services;

import com.gruzini.tennistico.emails.MessageCreator;
import com.gruzini.tennistico.emails.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final BeanFactory beanFactory;

    @Bean
    private ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(3, 3, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    public EmailService(JavaMailSender javaMailSender, BeanFactory beanFactory) {
        this.javaMailSender = javaMailSender;
        this.beanFactory = beanFactory;
    }

    public void sendEmail(final String email, final String tokenValue, final MessageType messageType) {
        final SimpleMailMessage message = prepareMailMessage(email, tokenValue, messageType);

        threadPoolExecutor().execute(() -> {
            javaMailSender.send(message);
            log.info("Email message was sent to " + message.getFrom());
        });
    }

    private SimpleMailMessage prepareMailMessage(final String email, final String tokenValue, final MessageType messageType) {
        final MessageCreator messageCreator = chooseMessageCreator(messageType);
        return messageCreator.create(email, tokenValue);
    }

    private MessageCreator chooseMessageCreator(final MessageType messageType) {
        return beanFactory.getBean(messageType.getComponentName(), messageType.getComponentClass());
    }
}
