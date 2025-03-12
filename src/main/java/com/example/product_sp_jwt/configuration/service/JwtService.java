package com.example.product_sp_jwt.configuration.service;

import com.example.product_sp_jwt.configuration.dto.UserPrinciple;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SignatureException;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "123456789987654321123456789987654321123456789";
    private static final long EXPIRE_TIME = 86400000L;

    public String generateTokenLogin(Authentication authentication) {
        UserPrinciple user = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((user.getUsername()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME))
                .signWith(SignInKey(),SignatureAlgorithm.HS256)
                .compact();

    }


    private Key SignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parse(authToken);
            return true;

        }catch (MalformedJwtException e){
            System.out.println("ma thong bao khong hop le -> Message: " + e.getMessage());
        }catch (ExpiredJwtException e){
            System.out.println("Mã thông báo JWT hết hạn -> Message: " + e.getMessage());
        }catch (UnsupportedJwtException e){
            System.out.println("Mã thông báo JWT không được hỗ trợ -> Message: " + e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println("JWT tuyên bố chuỗi là rỗng -> Message: " + e.getMessage());
        }
        return false;

    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
