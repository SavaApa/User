package org.example.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/anyone")
public class CompatibilityController {
    @GetMapping("/{id}/compatibility")
    public ResponseEntity<Map<String, Object>> getCompatibility(@PathVariable String id) {
        Map<String, Object> compatibilityData = new HashMap<>();
        compatibilityData.put("goals", Map.of("count", "1/3", "names", List.of("goal1", "goal2")));
        compatibilityData.put("values", Map.of("count", "2/3", "names", List.of("value1", "value2")));
        compatibilityData.put("interests", Map.of("count", "2/8", "names", List.of("interest1", "interest2")));
        compatibilityData.put("idealVacation", "By the sea");
        compatibilityData.put("lifestyle", "Active");
        compatibilityData.put("compatibility", 45);
        compatibilityData.put("psychotype", List.of(
                Map.of("name", "Vasya Johnson", "type", "Inspiring leader"),
                Map.of("name", "Vasya Johnson", "type", "Balance queen")
        ));
        compatibilityData.put("difficulties", Map.of("difficult", "text", "avoid", "text"));
        compatibilityData.put("loveLanguages", Map.of("count", "2/2", "names", List.of("lovelanguage1", "lovelanguage2"), "conclusion", "text"));

        return ResponseEntity.ok(compatibilityData);
    }
}
