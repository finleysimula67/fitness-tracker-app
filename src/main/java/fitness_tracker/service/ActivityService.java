package fitness_tracker.service;

import fitness_tracker.dto.ActivityRequest;
import fitness_tracker.dto.ActivityResponse;
import fitness_tracker.model.Activity;
import fitness_tracker.model.User;
import fitness_tracker.repository.ActivityRepository;
import fitness_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the "Workout Manager" engine.
 * It contains the business rules for handling and sorting workout records.
 */
@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    /** Saves a brand new workout record for a user */
    public ActivityResponse trackActivity(ActivityRequest request) {

        /** Double check that the person logging the workout actually exists in our system */
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id:" +request.getUserId()));

        /** Assemble a new Activity data object with all details provided by the user */
        Activity activity = Activity.builder()
                .user(user)
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        /** Tell the database librarian to permanently save this workout record */
        Activity savedActivity = activityRepository.save(activity);

        /** Re-package the saved info into a neat format to show back on the user's screen */
        return mapToResponse(savedActivity);
    }

    /** A helper translation tool that changes a database file into a clean layout for user screens */
    private ActivityResponse mapToResponse(Activity savedactivity) {

        ActivityResponse response = new ActivityResponse();
        response.setType(savedactivity.getType());
        response.setUser_id(savedactivity.getUser().getId());
        response.setAdditionalMetrics(savedactivity.getAdditionalMetrics());
        response.setDuration(savedactivity.getDuration());
        response.setId(savedactivity.getId()     );
        response.setCaloriesBurned(savedactivity.getCaloriesBurned());
        response.setStartTime(savedactivity.getStartTime());
        response.setCreatedAt(savedactivity.getCreatedAt());
        response.setUpdatedAt(savedactivity.getUpdatedAt());

        return response;
    }

    /** Pulls up a clean list of all historical workouts registered under one specific user ID */
    public List<ActivityResponse> getUserActivities(String userId) {

        List<Activity> activityList = activityRepository.findByUserId(userId);
        return activityList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}