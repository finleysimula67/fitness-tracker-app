package fitness_tracker.dto;

import fitness_tracker.model.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {

    private String id;

    private String user_id;

    private ActivityType type;

    private Map<String, Object> additionalMetrics;

    private Integer duration;

    private Integer caloriesBurned;

    private LocalDateTime startTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}