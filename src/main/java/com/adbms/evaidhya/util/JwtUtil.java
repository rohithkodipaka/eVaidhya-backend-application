package com.adbms.evaidhya.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    public static final String SECRET_KEY = "sdgshrasgiuagehjfblsuh35y498y49erhgiljsfgsfgusrbu348oy5284ytro8ufid";


    private String createToken(Map<String,Object> claims, String userName){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(SignatureAlgorithm.HS256, getSignKey())
                .compact();
    }

    private SecretKey getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String userName){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userName);
    }

    private Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpirationDate(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public String getUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public Boolean isTokenExpired(String token){
        return getExpirationDate(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String userName = getUserName(token);
        return (userName.equals(userDetails.getUsername())&& !isTokenExpired(token));

    }
}
