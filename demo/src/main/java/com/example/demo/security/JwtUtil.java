package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
  private static final long EXP_MS = 1000 * 60 * 60; // 1 hora
  private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  public String generate(org.springframework.security.core.Authentication auth){
    Date now = new Date();
    return Jwts.builder()
      .setSubject(auth.getName())
      .setIssuedAt(now)
      .setExpiration(new Date(now.getTime() + EXP_MS))
      .signWith(key)
      .compact();
  }

  public boolean isValid(String token){
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      return true;
    } catch (JwtException e){ return false; }
  }

  public String extractUsername(String token){
    return Jwts.parserBuilder().setSigningKey(key).build()
      .parseClaimsJws(token).getBody().getSubject();
  }
}
