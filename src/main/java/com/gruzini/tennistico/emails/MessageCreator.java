package com.gruzini.tennistico.emails;

import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface MessageCreator {
    MimeMessage create(final String email, final String content);
}
