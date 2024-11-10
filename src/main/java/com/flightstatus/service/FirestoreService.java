package com.flightstatus.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

    private final Firestore firestore;

    @Autowired
    public FirestoreService(FirebaseApp firebaseApp) {
        this.firestore = FirestoreClient.getFirestore(firebaseApp);
    }

    public List<Map<String, Object>> getCollectionData(String collectionName) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = firestore.collection(collectionName);
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        QuerySnapshot querySnapshot = future.get();
        return querySnapshot.getDocuments().stream()
                .map(DocumentSnapshot::getData)
                .toList();
    }

    public List<Map<String, Object>> getSubscriptions(String userId) throws ExecutionException, InterruptedException {
        CollectionReference subscriptionsRef = firestore.collection("UserSubscriptions")
                .document(userId)
                .collection("Subscriptions");

        QuerySnapshot querySnapshot = subscriptionsRef.get().get();
        return querySnapshot.getDocuments().stream()
                .map(DocumentSnapshot::getData)
                .toList();
    }

//    public List<Map<String, Object>> getUserSubscriptions() throws ExecutionException, InterruptedException {
//        CollectionReference collectionReference = firestore.collection("UserSubscriptions");
//        QuerySnapshot querySnapshot = collectionReference.get().get();
//        return querySnapshot.getDocuments().stream()
//                .map(DocumentSnapshot::getData)
//                .toList();
//    }
//
//    public Map<String, Object> getUserSubscription(String documentId) throws ExecutionException, InterruptedException {
//        DocumentReference documentReference = firestore.collection("UserSubscriptions").document(documentId);
//        DocumentSnapshot documentSnapshot = documentReference.get().get();
//        return documentSnapshot.getData();
//    }
}
