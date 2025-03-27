package com.example.demo.service;

import com.example.demo.configuration.AdminConfig;
import com.example.demo.configuration.TwilioMailConfig;
import com.example.demo.configuration.TwilioSmsConfig;
import com.example.demo.configuration.TwilioWhatsappConfig;
import com.example.demo.model.Orders;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AdminMessagingService {

    @Autowired
    AdminConfig adminConfig;
    @Autowired
    TwilioMailConfig twilioMailConfig;
    @Autowired
    TwilioSmsConfig twilioSmsConfig;
    @Autowired
    TwilioWhatsappConfig twilioWhatsappConfig;

    @Autowired
    JavaMailSender mailSender;

    public void sendSms(Orders orders) {
        try {
            Message.creator(new PhoneNumber(adminConfig.getPhoneNumber()),
                            new PhoneNumber(twilioSmsConfig.getTrialNumber()),
                            "Alert: orders " + orders.getOid() + "successfully placed.")
                    .create();
        } catch (Exception e) {
            System.out.println("failed sms");
        }
    }

    public void sendwhatsapp(Orders orders){
        try {
            Message.creator(new PhoneNumber(adminConfig.getWhatsappNumber()),
                    new PhoneNumber(twilioWhatsappConfig.getTrialNumber()),
                    "Alert: orders "+orders.getOid()+"successfully orders.").create();
        }
        catch (Exception e){
            System.out.println("failed Whatsapp msg");
        }

    }

    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminConfig.getEmail());
        message.setFrom(twilioMailConfig.getUsername());
        message.setSubject("hii");
        message.setText("helloo this is messagee");
        mailSender.send(message);
    }

    public void sendEmailWithAttachment(String filePath) {
        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(adminConfig.getEmail());
            helper.setSubject("stock reports");
            helper.setText(" report successfully");
            helper.setFrom(twilioMailConfig.getUsername());

            File file = new File(filePath);
            helper.addAttachment(file.getName(), file);
            mailSender.send(message);

            System.out.println("Email sent successfully with attachment!");
        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }

}
