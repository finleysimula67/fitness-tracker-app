package fitness_tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This file represents a user's account profile details
 * stored securely in the "users" database table.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    /** A unique, random ID string given to each registered person */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /** The person's account email address */
    private String email;

    /** The person's secure, hidden password */
    private String password;

    /** The person's first name */
    private String firstName;

    /** The person's last name */
    private String lastName;

    /** The user's account clearance level (Defaults to standard USER) */
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    /** Automatically logs when this user first created their account */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /** Automatically updates whenever the user edits their profile details */
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /** A complete history of all workout logs belonging to this specific user */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<Activity> activities = new ArrayList<>();

    /** A complete history of all advice messages generated for this specific user */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<Recommendation> recommendations = new ArrayList<>();
}