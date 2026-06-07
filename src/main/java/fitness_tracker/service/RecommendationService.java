package fitness_tracker.service;

import fitness_tracker.dto.RecommendationRequest;
import fitness_tracker.model.Activity;
import fitness_tracker.model.Recommendation;
import fitness_tracker.model.User;
import fitness_tracker.repository.ActivityRepository;
import fitness_tracker.repository.RecommendationRepository;
import fitness_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public Recommendation generateRecommendation(RecommendationRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("User not found: "+request.getUserId()));

        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(()->new RuntimeException("Activity not found: "+request.getActivityId()));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .activity(activity)
                .improvements(request.getImprovements())
                .suggestions(request.getSuggestions())
                .safety(request.getSafety())
                .type(request.getType())
                .recommendation(request.getRecommendation())
                .build();

        return recommendationRepository.save(recommendation);
    }

    public List<Recommendation> getUserRecommendation(String userId)
    {
        return recommendationRepository.findByUserId(userId);
    }

    public List<Recommendation> getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId);
    }
}
