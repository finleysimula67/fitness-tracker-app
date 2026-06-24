package fitness_tracker.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

/**
 * This file is the "Token Machine."
 * It knows how to create, read, and verify secure digital login keys (JWT tokens).
 */
@Component
public class JwtUtils {

    /** The private password only our system knows, used to sign keys so they can't be faked */
    @Value("${jwt.secret}")
    private String jwtSecret;

    /** How long a digital key stays valid before it expires and the user must log in again */
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /** Creates a highly secure mathematical key out of your private secret text */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    /** Generate JWT Token */
    public String generateToken(String userId , String role) {

        /** Builds a digital login ticket stamped with the user's ID, their role, and an expiration time */
        return Jwts.builder()
                .subject(userId)
                .claim("roles", List.of(role))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    /** Extract Email/Username */
    public String extractUsername(String token) {

        /** Decodes a token and reads out the owner's unique user ID */
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /** Validate Token */
    public boolean validateToken(String token) {

        try {
            /** Tries to open the key using our secret handshake; if it's altered or old, it crashes */
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (JwtException | IllegalArgumentException e) {
            /** Returns false if the key is a fake, broken, or expired */
            return false;
        }
    }

    /** Decodes a token and reads the exact security access list stored inside it */
    public List<String> extractRoles(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("roles", List.class);
    }
}