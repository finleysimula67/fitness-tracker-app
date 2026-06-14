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


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest request) {

        UserRole role = request.getRole() != null ? request.getRole()
                : UserRole.USER;
        User user = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

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

    public User authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) { throw new RuntimeException("Invalid Credentials");}
        if (!passwordEncoder.matches( loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");}
        return user;
    }
}