package com.example.demo.configuration;

import com.twilio.Twilio;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
public class TwilioSmsConfig {
    private String trialNumber;
    public static final String accountSid = "ACa949ea4dc9ad901ab16f28b864468c82";
    public static final String authId = "e34fabd9f7d3c12fb39594523236dcab";

    static {
        Twilio.init(accountSid, authId);
    }

    public TwilioSmsConfig(){}

    public TwilioSmsConfig(String trialNumber) {
        this.trialNumber = trialNumber;
    }

    public String getTrialNumber() {
        return trialNumber;
    }

    public void setTrialNumber(String trialNumber) {
        this.trialNumber = trialNumber;
    }
}

