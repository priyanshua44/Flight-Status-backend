package com.flightstatus.service;

import com.flightstatus.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class NotificationService {

    @Autowired
    private FlightService flightService;

    private final FirestoreService firestoreService;
    private final EmailService emailService;
    private final FlightStatusService flightStatusService;

    @Autowired
    public NotificationService(FirestoreService firestoreService, EmailService emailService, FlightStatusService flightStatusService) {
        this.firestoreService = firestoreService;
        this.emailService = emailService;
        this.flightStatusService = flightStatusService;
    }

    public void sendSubscriptionEmails(String userId) {
        try {
            System.out.println("Fetching subscriptions for user: " + userId);
            List<Map<String, Object>> subscriptions = firestoreService.getSubscriptions(userId);

            for (Map<String, Object> subscription : subscriptions) {
                Map<String, Object> userDetails = (Map<String, Object>) subscription.get("userDetails");
                String email = (String) userDetails.get("email");
                Long dataId = (Long) subscription.get("dataId");

                if (email == null || email.isEmpty()) {
                    System.err.println("Skipped sending email. Email address is null or empty for subscription: " + subscription);
                    continue;
                }

//               convert dataId from string to long
//                Long flightId = Long.parseLong(dataId);

                // Fetch flight status using dataID
                Flight flight = flightService.getFlightById(dataId);
                String flightStatus = flight.getFlightStatus().getDescription();
                String flightNumber = flight.getFlightNumber();
                System.out.println("Hello, the current status of your Flight (Number: " + flightNumber + ") is: " + flightStatus);
                // Customize your email content
                String subject = "Flight Status Update " + flightNumber;
                String body = "Hello, the current status of your Flight (Number: " + flightNumber + ") is: " + flightStatus;

                System.out.println("Sending email to: " + email);
                // Send email
                emailService.sendEmail(email, subject, body);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
}
