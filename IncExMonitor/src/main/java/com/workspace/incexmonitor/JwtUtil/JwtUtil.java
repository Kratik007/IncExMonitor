package com.workspace.incexmonitor.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Service
public class JwtUtil {

	
//    private String SECRET_KEY = new String("my-32-character-ultra-secure-and-ultra-long-secretmy-32-character-ultra-secure-and-ultra-long-secretmy-32-character-ultra-secure-and-ultra-long-secretmy-32-character-ultra-secure-and-ultra-long-secretmy-32-character-ultra-secure-and-ultra-long-secretmy-32-character-ultra-secure-and-ultra-long-secretmy-32-character-ultra-secure-and-ultra-long-secretmy-32-character-ultra-secure-and-ultra-long-secretmy-32-character-ultra-secure-and-ultra-long-secretmy-32-character-ultra-secure-and-ultra-long-secretmy-32-character-ultra-secure-and-ultra-long-secretmy-32-character-ultra-secure-and-ultra-long-secret");
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    @SuppressWarnings("deprecation")
	private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, key).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}