package fitness_tracker.dto;

import fitness_tracker.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This file is a sign-up form. A brand new user fills this out
 * to create an account in our system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    /** The user's chosen email (System checks to make sure it's valid and not blank) */
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    /** The password the user wants to use */
    private String password;
    /** The user's first name */
    private String firstName;
    /** The user's last name */
    private String lastName;
    /** The level of access given to this account (like USER or ADMIN) */
    private UserRole role;
}