package org.example.user.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/anyone")
public class SupportController {
    private final Firestore db;

    @PostMapping("/support")
    public ResponseEntity<Map<String, Object>> createSupportRequest(@RequestBody Map<String, Object> request) {
        DocumentReference docRef = db.collection("support").document();

        Map<String, Object> data = new HashMap<>();
        if (request.containsKey("user_id")) {
            data.put("user_id", request.get("user_id"));
        }
        if (request.containsKey("email")) {
            data.put("email", request.get("email"));
        }
        data.put("subject", request.get("subject"));
        data.put("message", request.get("message"));

        ApiFuture<WriteResult> result = docRef.set(data);

        try {
            result.get();
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("success", "Support request created successfully."));
        } catch (InterruptedException | ExecutionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An error occurred while creating the support request."));
        }
    }
}
