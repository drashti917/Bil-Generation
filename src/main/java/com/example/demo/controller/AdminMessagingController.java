package com.example.demo.controller;


import com.example.demo.model.Orders;
import com.example.demo.service.AdminMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminMessagingController {
    @Autowired
    AdminMessagingService adminMessagingService;

    @PostMapping("send-sms")
    public String sendsms(@RequestBody Orders orders) {
        adminMessagingService.sendSms(orders);
        return "SMS Sent Successfully!";
    }

    @PostMapping("send-whatsapp")
    public String sendwhatsapp(@RequestBody Orders orders) {
        adminMessagingService.sendwhatsapp(orders);
        return "whatsapp msg Sent Successfully!";
    }

    @PostMapping("send-email")
    public String sendEmail() {
        adminMessagingService.sendEmail();
        return "Email sent successfully";
    }

    @PostMapping("sendemailwithattachment")
    public String sendEmailWithAttachment(@RequestParam String filePath) {
        adminMessagingService.sendEmailWithAttachment(filePath);
        return "csv report successfully mail";
    }
}
