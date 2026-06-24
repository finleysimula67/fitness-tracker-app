package fitness_tracker.dto;

import fitness_tracker.model.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * This file is a form used when a user wants to log a new workout.
 * It carries the workout details from the user's phone to our system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {

    /** The unique ID of the person doing the workout */
    private String userId;

    /** The type of workout (like RUNNING, SWIMMING, WALKING) */
    private ActivityType type;

    /** Extra custom details (like heart rate, weather, or steps) */
    private Map<String, Object> additionalMetrics;

    /** How long the workout lasted in minutes */
    private Integer duration;

    /** How many calories were burned during the workout */
    private Integer caloriesBurned;

    /** The exact date and time the workout started */
    private LocalDateTime startTime;

}