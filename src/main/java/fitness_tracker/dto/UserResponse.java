package fitness_tracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * This file is used to show a user's account profile on the screen
 * without exposing hidden system configurations.
 */
@Data
@NoArgsConstructor

public class UserResponse {
    /** The unique ID number assigned to this user */
    private String id;
    /** The email address of the user */
    private String email;
    /** The user's encrypted password string */
    private String password;
    /** The user's first name */
    private String firstName;
    /** The user's last name */
    private String lastName;
    /** The exact date and time this account was created */
    private LocalDateTime createdAt;
    /** The exact date and time this account was last updated */
    private LocalDateTime updatedAt;
}