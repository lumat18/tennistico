package com.gruzini.tennistico.emails;

import org.springframework.mail.SimpleMailMessage;

public interface MessageCreator {
    SimpleMailMessage create(final String email, final String content);
}
