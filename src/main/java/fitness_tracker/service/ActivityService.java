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


@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public ActivityResponse trackActivity(ActivityRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id:" +request.getUserId()));

        Activity activity = Activity.builder()
                .user(user)
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        Activity savedActivity = activityRepository.save(activity);

        return mapToResponse(savedActivity);
    }


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

    public List<ActivityResponse> getUserActivities(String userId) {

        List<Activity> activityList = activityRepository.findByUserId(userId);
        return activityList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
