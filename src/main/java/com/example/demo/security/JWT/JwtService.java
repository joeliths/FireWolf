package com.example.demo.security.JWT;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final static String secretKey = "Secret"; //TODO: Store somewhere else and dont let that be the secret

    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public boolean isNotExpired(String token){
        try{
            Date date = getClaims(token).getExpiration();
            Date currentDate = new Date(System.currentTimeMillis());
            return currentDate.before(date);
        }catch(ExpiredJwtException e){
            return false;
        }
    }

    public String getSubject(String token){
        try{
            return getClaims(token).getSubject();
        }catch(JwtException e){
            throw e;
        }
    }


}
