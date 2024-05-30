package org.example.user.controller;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/anyone")
public class AnyoneController {
    private final Firestore db;
    private final Storage storage;

    @Autowired
    public AnyoneController(Firestore db) {
        this.db = db;
        this.storage = StorageOptions.getDefaultInstance().getService();
    }

    @PostMapping("/analyze")
    public ResponseEntity<String> analyzeAnyone(@RequestParam("foto") MultipartFile foto,
                                                @RequestParam("name") String name,
                                                @RequestParam("dateOfBirth") String dateOfBirth,
                                                @RequestParam("gender") int gender,
                                                @RequestParam("relationshipType") String relationshipType) throws IOException {
        String userId = "UserId";

        String fileName = UUID.randomUUID() + "-" + foto.getOriginalFilename();
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder("your-bucket-name", fileName).build(),
                foto.getInputStream()
        );
        String photoUrl = blobInfo.getMediaLink();

        Map<String, Object> analyzeData = new HashMap<>();
        analyzeData.put("name", name);
        analyzeData.put("dateOfBirth", dateOfBirth);
        analyzeData.put("gender", gender);
        analyzeData.put("relationshipType", relationshipType);
        analyzeData.put("photoUrl", photoUrl);

        DocumentReference docRef = db.collection("users").document(userId).collection("analyze").document("latest");
        docRef.set(analyzeData);
        return ResponseEntity.ok("Аналитические данные успешно сохранены.");
    }
}


