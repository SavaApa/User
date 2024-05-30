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
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/anyone")
public class LikeController {

    private final Firestore db;

    @PostMapping("/like")
    public ResponseEntity<String> likeUser(@RequestBody Map<String, Object> likeData) {
        String currentUserId = UUID.randomUUID().toString();
        String targetUserId = String.valueOf(likeData.get("user_id"));
        boolean like = Boolean.parseBoolean(String.valueOf(likeData.get("like")));

        DocumentReference docRef = db.collection("users").document(currentUserId).collection("likes").document(targetUserId);
        docRef.set(Map.of("liked", like));
        return ResponseEntity.ok("User liked successfully.");
    }
}
