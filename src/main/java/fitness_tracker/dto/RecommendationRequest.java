package fitness_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * This file collects information to generate health advice and tips
 * for a user based on how they performed in a workout.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RecommendationRequest {
    /** The ID of the user receiving the advice */
    private String userId;
    /** The ID of the specific workout this advice is for */
    private String activityId;
    /** A list of areas where the user can improve */
    private List<String> improvements;
    /** A list of helpful action steps or ideas */
    private List<String> suggestions;
    /** A list of safety warnings or rules to avoid injury */
    private List<String> safety;
    /** The category of advice being given */
    private String type;
    /** The final written message of advice for the user */
    private String recommendation;
}