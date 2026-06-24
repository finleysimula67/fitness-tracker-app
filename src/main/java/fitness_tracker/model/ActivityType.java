package fitness_tracker.model;

/**
 * This is an Enum, which is simply a fixed checklist of allowed options.
 * The app can only accept workouts that match one of these specific names.
 */
public enum ActivityType {
    RUNNING,
    WALKING,
    CYCLING,
    SWIMMING,
    WEIGHT_TRAINING,
    YOGA,
    HIIT,
    CARDIO,
    STRETCHING,
    OTHER
}