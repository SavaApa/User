package org.example.user.controller;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myself")
public class MyselfController {

    private final Firestore db;

    @PostMapping("/analyze")
    public ResponseEntity<String> analyzeMyself(@RequestBody Map<String, Object> analyzeData) {
        String userId = "UserId1";
        DocumentReference docRef = db.collection("users").document(userId).collection("analyze").document("latest");
        docRef.set(analyzeData);
        return ResponseEntity.ok("Analysis data saved successfully.");
    }
}
