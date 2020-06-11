package com.gruzini.tennistico.emails;

import javax.mail.internet.MimeMessage;

public interface MessageCreator {
    MimeMessage create(final String email, final String content);
}
