package fitness_tracker.controller;

import fitness_tracker.dto.RecommendationRequest;
import fitness_tracker.model.Recommendation;
import fitness_tracker.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor

public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(
           @RequestBody RecommendationRequest request
    ) {
        Recommendation recommendation = recommendationService.generateRecommendation(request);
        return ResponseEntity.ok(recommendation);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(
            @PathVariable String userId
    ) {
        List<Recommendation> recommendations = recommendationService.getUserRecommendation(userId);
        return ResponseEntity.ok(recommendations);
    }
}
