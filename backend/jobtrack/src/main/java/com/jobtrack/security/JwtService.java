package com.jobtrack.security;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.jobtrack.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	
	private String secretKey = "jobtrack-secret-key-12345678901234567890";
	private long expiration = 1000 * 60 * 60;
	
	public String generateToken(User user) {
		
		return Jwts.builder()
				.setSubject(user.getEmail())
				.claim("role", user.getRole())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.compact();
	}
	
	public String extractEmail(String token) {
		var claims = Jwts.parser()
		.verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
		.build()
		.parseSignedClaims(token)
		.getPayload();
		
		return claims.getSubject();
	}
}
