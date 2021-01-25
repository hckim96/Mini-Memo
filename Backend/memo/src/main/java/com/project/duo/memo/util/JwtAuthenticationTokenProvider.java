package com.project.duo.memo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtAuthenticationTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenProvider.class);

    private static final String SECRET_KEY = "SECRET";
    private static final long EXPIRATION_MS = 1000 * 60 * 60 * 24;

    public String parseTokenString(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public JwtAuthenticationToken issue(Long userNo) {
        return JwtAuthenticationToken.builder().token(buildToken(userNo)).build();
    }

    // JWT 토큰 생성
    private String buildToken(Long userNo) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredAt = now.plus(EXPIRATION_MS, ChronoUnit.MILLIS);
        return Jwts.builder()
                .setSubject(String.valueOf(userNo))
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiredAt.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public Long getTokenOwnerNo(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }


    public boolean validateToken(String token) {
        if (StringUtils.isNotEmpty(token)) {
            try {
                Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
                return true;
            } catch (SignatureException e) {
                logger.error("Invalid JWT signature", e);
            } catch (MalformedJwtException e) {
                logger.error("Invalid JWT token", e);
            } catch (ExpiredJwtException e) {
                logger.error("Expired JWT token", e);
            } catch (UnsupportedJwtException e) {
                logger.error("Unsupported JWT token", e);
            } catch (IllegalArgumentException e) {
                logger.error("JWT claims string is empty.", e);
            }
        }
        return false;
    }
}
