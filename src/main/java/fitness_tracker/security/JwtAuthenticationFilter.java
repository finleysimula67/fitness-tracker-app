package fitness_tracker.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * This is an automatic ID checker at the entrance of your app.
 * For every web request coming in, it checks if the visitor brought a valid digital key.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        /** Look for a secret login key stamped inside the request's header information */
        String header = request.getHeader("Authorization");

        /** Check if the key exists and if it begins with the standard word "Bearer " */
        if (header != null && header.startsWith("Bearer ")) {

            /** Chop off the word "Bearer " to look closely at just the raw security code string */
            String token = header.substring(7);

            /** Ask our digital key scanner tool if this token is genuine and hasn't expired */
            if (jwtUtils.validateToken(token)) {

                /** Read the unique user ID locked inside the token */
                String userId = jwtUtils.extractUsername(token);

                /** Read the clearance level (like USER or ADMIN) locked inside the token */
                List<String> roles = jwtUtils.extractRoles(token);

                /** Turn those text badges into real security clearances the system understands */
                List<SimpleGrantedAuthority> authorities =
                        roles.stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                                .toList();

                /** Create an official security badge for this user session */
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userId,
                                null,
                                authorities
                        );

                /** Pin this security badge onto the current request so the system lets them in */
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        /** Pass the visitor along to the next step of the application */
        filterChain.doFilter(request, response);
    }
}