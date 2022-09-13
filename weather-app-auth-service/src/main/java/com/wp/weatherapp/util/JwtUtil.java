package com.wp.weatherapp.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import com.wp.weatherapp.exception.InvalidTokenException;

import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {

	 private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	    public String generateToken(String useremail) {
	        long currentMillis = System.currentTimeMillis();
	        long expiryMillis = currentMillis + (60 * 60 * 1000 * 24);
	        Date expiryDate = new Date(expiryMillis);
	        String token = Jwts.builder()
	                .signWith(key)
	                .setSubject(useremail)
	                .setExpiration(expiryDate)
	                .compact();
	        return token;
	    }


	    public String decodeToken(String token) throws InvalidTokenException {
	        if (token == null || token.isEmpty()) {
	            throw new InvalidTokenException("invalid token");
	        }
	        token = token.trim();
	        Jws<Claims> jws = Jwts.parserBuilder()
	                .setSigningKey(key)
	                .build()
	                .parseClaimsJws(token);
	        Claims claims = jws.getBody();
	        String useremail = claims.getSubject();
	        return useremail;
	    }
	
	
}
