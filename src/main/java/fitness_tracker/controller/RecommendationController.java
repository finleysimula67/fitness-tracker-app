package fitness_tracker.controller;

import fitness_tracker.dto.RecommendationRequest;
import fitness_tracker.model.Recommendation;
import fitness_tracker.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This file is the "Advisor" for your app.
 * It manages and hands out fitness advice or tips (recommendations) to the user.
 */
@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor

public class RecommendationController {

    private final RecommendationService recommendationService;

    /**POST:
     This method provides the feature of creating new fitness tips or advice
     using the details sent in the Recommendation Request.
     */
    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(
            @RequestBody RecommendationRequest request
    ) {
        Recommendation recommendation = recommendationService.generateRecommendation(request);
        return ResponseEntity.ok(recommendation);
    }

    /**GET:
     This method provides the feature of fetching all saved tips or advice
     belonging to a specific person by using their User ID from the web link.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(
            @PathVariable String userId
    ) {
        List<Recommendation> recommendations = recommendationService.getUserRecommendation(userId);
        return ResponseEntity.ok(recommendations);
    }

    /**GET:
     This method provides the feature of fetching tips or advice connected to a
     specific workout or task by using the Activity ID from the web link.
     */
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<Recommendation>> getActivityRecommendation(
            @PathVariable String activityId
    ) {
        List<Recommendation> recommendations = recommendationService.getActivityRecommendation(activityId);
        return ResponseEntity.ok(recommendations);
    }
}