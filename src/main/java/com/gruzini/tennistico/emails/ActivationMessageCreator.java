package com.gruzini.tennistico.emails;

import com.gruzini.tennistico.models.forms.VerificationEmail;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component("activation")
public class ActivationMessageCreator implements MessageCreator {
    private final JavaMailSender javaMailSender;

    private static final String SUBJECT = "Welcome to Tennistico!";
    private static final String TEXT = "In order to activate you account, please click the link below: \n";

    public ActivationMessageCreator(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @SneakyThrows
    @Override
    public MimeMessage create(String email, String tokenValue) {
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject(SUBJECT);
        helper.setTo(email);
        helper.setText(new VerificationEmail(createActivationLink(tokenValue)).getBody(), true);
        return mimeMessage;
    }

    //TODO change link to real request address
    private String createActivationLink(final String tokenValue) {
        return "http://localhost:8080/activate?value=" + tokenValue;
    }
}
