package fitness_tracker.dto;

import fitness_tracker.model.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * This file is a digital receipt sent back to the user's phone
 * after a workout is saved, showing them all the stored details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {

    /** The unique ID number given to this specific workout entry */
    private String id;

    /** The ID number of the person who did the workout */
    private String user_id;

    /** The type of workout done */
    private ActivityType type;

    /** Extra custom details stored for this workout */
    private Map<String, Object> additionalMetrics;

    /** How long the workout lasted */
    private Integer duration;

    /** The total calories burned */
    private LocalDateTime startTime;

    /** The exact time this workout record was first saved */
    private LocalDateTime createdAt;

    /** The exact time this workout record was last changed or edited */
    private LocalDateTime updatedAt;

    /** The total calories burned */
    private Integer caloriesBurned;

}