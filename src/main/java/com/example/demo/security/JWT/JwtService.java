package com.example.demo.security.JWT;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final static String secretKey = "Secret";

    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String getSubject(String token) throws JwtException {
      return getClaims(token).getSubject();
    }

}
