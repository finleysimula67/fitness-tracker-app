package fitness_tracker.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This file is the "Security Guard" for your app.
 * It locks the doors and decides who is allowed to enter.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    /** This is the tool that checks if a user has a valid login key. */
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * This sets up the house rules for who can visit which pages.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizerRequests->
                        authorizerRequests.requestMatchers("/api/admin/**").hasRole("ADMIN") /** Only Admins can see admin pages */
                                .requestMatchers("/api/auth/**").permitAll() /** Anyone can see login/register pages */
                                .requestMatchers("/swagger-ui.html",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**").permitAll() /** Anyone can see the help/documentation pages */
                                .anyRequest().authenticated()); /** Everything else requires you to be logged in */
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); /** Check the user's key before letting them pass */
        return http.build();
    }

    /**
     * This is a "Password Scrambler." It turns real passwords into
     * random text so hackers cannot read them in the database.
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}