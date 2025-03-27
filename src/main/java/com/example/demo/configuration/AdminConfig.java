package com.example.demo.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "admin")
public class AdminConfig {
    private String name;
    private String email;
    private String PhoneNumber;
    private String whatsappNumber;

public AdminConfig(){}

    public AdminConfig(String name, String email, String phoneNumber, String whatsappNumber) {
        this.name = name;
        this.email = email;
        this.PhoneNumber = phoneNumber;
        this.whatsappNumber = whatsappNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }
}
