package com.gruzini.tennistico.models.emails;

import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.StringSubstitutor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Data
public class ActivationEmail {

    private static final String ACTIVATION_EMAIL_TEMPLATE_LOCATION = "/static/emailTemplate/activation.html";

    private String verificationLink;

    public ActivationEmail(String verificationLink) {
        this.verificationLink = verificationLink;
    }

    public String getBody() {
        String fileToString = getStringFromResourceTemplate();
        return getStringSubstitutor().replace(fileToString);
    }

    private String getStringFromResourceTemplate() {
        URL url = getClass().getResource(ACTIVATION_EMAIL_TEMPLATE_LOCATION);
        File file = new File(url.getPath());
        String fileToString = "";
        try {
            fileToString = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileToString;
    }

    private StringSubstitutor getStringSubstitutor() {
        Map<String, String> values = new HashMap<>();
        values.put("link", this.verificationLink);
        return new StringSubstitutor(values);
    }

}
