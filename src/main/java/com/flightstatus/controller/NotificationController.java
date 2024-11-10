package com.flightstatus.controller;

import com.flightstatus.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/send/{userId}")
    public String sendSubscriptionEmails(@PathVariable String userId) {
        notificationService.sendSubscriptionEmails(userId);
        return "Emails sent to all subscriptions for user " + userId;
    }
}
