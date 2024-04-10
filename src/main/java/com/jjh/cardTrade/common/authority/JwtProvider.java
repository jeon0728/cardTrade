package com.jjh.cardTrade.common.authority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration-minutes}")
    private Long expirationMinutes;

    // 토큰 생성 메소드
    public String createToken(String cardRefId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofMinutes(expirationMinutes).toMillis()); // 만료기간 1분

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("jjh") // 토큰발급자
                .setIssuedAt(now) // 발급시간
                .setExpiration(expiration) // 만료시간
                .setSubject(cardRefId) //  토큰 제목
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes())) // 알고리즘, 시크릿 키
                .compact();
    }

    // Jwt 토큰의 유효성 체크 메소드
    public Claims parseJwtToken(String token) {
        token = BearerRemove(token); // Bearer 제거
        System.out.println(token);
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰 앞 부분('Bearer') 제거 메소드
    private String BearerRemove(String token) {
        return token.substring("Bearer ".length());
    }
}
