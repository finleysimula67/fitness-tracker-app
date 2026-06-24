package fitness_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This file is like a login slip. The user types their email
 * and password into it to prove who they are.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    /** The email address of the account */
    private String email;
    /** The password for the account */
    private String password;
}