package fitness_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This file is the system's reply after a successful login.
 * It hands the user their digital key and profile information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoginResponse {
    /** The secure digital key (token) used to stay logged in */
    private String token;
    /** The profile details of the user who just logged in */
    private UserResponse userResponse;
}