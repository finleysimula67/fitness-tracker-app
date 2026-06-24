package fitness_tracker.service;

import fitness_tracker.model.User;
import fitness_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This is an automated link that helps our Security Guard load
 * profile data directly from our database using an email address.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        /** Search the storage room to find a user entry with this email address */
        User user = userRepository.findByEmail(email);

        /** If no user is found with that email, trigger an error message stop */
        if(user == null ) {
            throw new RuntimeException("User not Found "+ email);
        }

        /** Package the user's email, scrambled password, and authority level into a system-ready safety file */
        return  org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}