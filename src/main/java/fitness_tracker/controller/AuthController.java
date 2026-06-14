package fitness_tracker.controller;

import fitness_tracker.dto.LoginRequest;
import fitness_tracker.dto.LoginResponse;
import fitness_tracker.dto.RegisterRequest;
import fitness_tracker.dto.UserResponse;
import fitness_tracker.model.User;
import fitness_tracker.repository.UserRepository;
import fitness_tracker.security.JwtUtils;
import fitness_tracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
      User user = userService.authenticate(loginRequest);
      String token = jwtUtils.generateToken(user.getId(), user.getRole().name());
      return ResponseEntity.ok(new LoginResponse(token, userService.mapToResponse(user)));
    }
}