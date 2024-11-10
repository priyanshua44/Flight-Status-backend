package com.flightstatus.controller;

import com.flightstatus.service.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/firestore")
public class FirestoreController {

    private final FirestoreService firestoreService;

    @Autowired
    public FirestoreController(FirestoreService firestoreService) {
        this.firestoreService = firestoreService;
    }

    @GetMapping("/collection")
    public List<Map<String, Object>> getCollectionData() {
        try {
            return firestoreService.getCollectionData("UserSubscriptions");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch data from Firestore", e);
        }
    }

    @GetMapping("/user/{userId}")
    public List<Map<String, Object>> getUserSubscriptions(@PathVariable String userId) {
        try {
            return firestoreService.getSubscriptions(userId);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching subscriptions", e);
        }
    }
}
