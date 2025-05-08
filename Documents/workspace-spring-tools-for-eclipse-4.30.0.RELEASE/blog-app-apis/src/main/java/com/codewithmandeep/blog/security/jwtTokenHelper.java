package com.codewithmandeep.blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class jwtTokenHelper {
	  // Token validity (5 hours)
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

    // Secret key (should be moved to application.properties in real-world apps)
    private final String secret = "mySecretKey1234567890";  // Minimum 256-bit key for HS512

    // Retrieve username from token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Retrieve expiration date from token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Get a specific claim
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Get all claims using the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    // Check if token has expired
    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Generate token for user
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

    // Build token
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // Validate token
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String tokenUsername = getUsernameFromToken(token);
//        return (tokenUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }

	public boolean validateToken(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		final String tokenUsername = getUsernameFromToken(token);
		
		return (tokenUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String generateToken(UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}
}

    


	