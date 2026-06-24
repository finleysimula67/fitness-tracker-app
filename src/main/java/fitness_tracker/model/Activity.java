package fitness_tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This file represents a single workout record (like a run or gym session)
 * that will be saved in the database.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {

    /** A unique, random ID string given to this specific workout log */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /** Links this workout to the specific User who performed it */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_activity_user")
    )
    @JsonIgnore
    private User user;

    /** The type of exercise chosen from the list below (like RUNNING or YOGA) */
    @Enumerated(EnumType.STRING)
    private ActivityType type;

    /** A flexible storage space for custom metrics, saved in an advanced format (JSON) */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> additionalMetrics;

    /** How long the workout lasted in minutes */
    private Integer duration;

    /** Total number of calories burned */
    private Integer caloriesBurned;

    /** The exact date and time the user started the exercise */
    private LocalDateTime startTime;

    /** Automatically saves the exact date and time when this record was created */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /** Automatically updates the date and time whenever this record is edited */
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /** A list of all AI fitness tips or advice generated for this specific workout */
    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    @Builder.Default
    private List<Recommendation> recommendations = new ArrayList<>();
}