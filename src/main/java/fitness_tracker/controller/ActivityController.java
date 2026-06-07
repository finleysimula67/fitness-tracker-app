package fitness_tracker.controller;

import fitness_tracker.dto.ActivityRequest;
import fitness_tracker.dto.ActivityResponse;
import fitness_tracker.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request) {
        return ResponseEntity.ok(activityService.trackActivity(request));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivities(
            @RequestHeader(value = "X-User_ID") String userId
    ) {
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }

}
