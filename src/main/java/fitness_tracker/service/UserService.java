package fitness_tracker.service;

import fitness_tracker.dto.LoginRequest;
import fitness_tracker.dto.RegisterRequest;
import fitness_tracker.dto.UserResponse;
import fitness_tracker.model.User;
import fitness_tracker.model.UserRole;
import fitness_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This is the "Account Manager" engine.
 * It handles signup registrations and logs users safely into the app.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /** Creates a bran-new account inside the system */
    public UserResponse register(RegisterRequest request) {

        /** Give the account standard "USER" clearance if no special role was requested */
        UserRole role = request.getRole() != null ? request.getRole()
                : UserRole.USER;

        /** Construct a new user file, scrambles their password for safety, and readies it */
        User user = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        /** Tell the database librarian to save this profile */
        User savedUser = userRepository.save(user);

        /** Map the result to a clean profile copy to send back to the screen */
        return mapToResponse(savedUser);
    }

    /** Converts a database user profile file into a clean information layout for display */
    public UserResponse mapToResponse(User savedUser) {
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setPassword(savedUser.getPassword());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());
        return response;
    }

    /** Verifies a user's login form credentials */
    public User authenticate(LoginRequest loginRequest) {
        /** Find the profile matched with this login email */
        User user = userRepository.findByEmail(loginRequest.getEmail());

        /** If the email does not exist, cancel login and throw an error */
        if (user == null) { throw new RuntimeException("Invalid Credentials");}

        /** Compare the typed password with the scrambled version stored in the database */
        if (!passwordEncoder.matches( loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");}

        /** If everything matches up perfectly, pass back the authenticated user profile */
        return user;
    }
}