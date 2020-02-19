package it.joeg.inus.be.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

/**
 * Utility class for generating and verifying JWT
 */
public class JwtTokenProvider {

    private static final Logger LOG = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private long jwtExpirationInMs;

    @Value("${app.jwtRenewIfExpiresIn}")
    private long jwtRenewIfExpiresIn;

    @Value("${app.jwtMsToAddAtRenew}")
    private long jwtMsToAddAtRenew;

    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userPrincipal.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserIdFromJWT(String token) {
        Claims claims = getClaimsFromJWT(token);
        return claims.getSubject();
    }

    private Claims getClaimsFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public Date getExpirationFromJWT(String token) {
        Claims claims = getClaimsFromJWT(token);
        return claims.getExpiration();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            LOG.error("Invalid JWT token {}", ex.getLocalizedMessage());
        } catch (ExpiredJwtException ex) {
            LOG.error("Expired JWT token {}", ex.getLocalizedMessage());
        } catch (UnsupportedJwtException ex) {
            LOG.error("Unsupported JWT token {}", ex.getLocalizedMessage());
        } catch (IllegalArgumentException ex) {
            LOG.error("JWT claims string is empty {}", ex.getLocalizedMessage());
        }
        return false;
    }

    public String renewJWT(String token) {
        Claims claims = getClaimsFromJWT(token);
        long expirationMillis = claims.getExpiration().toInstant().toEpochMilli();
        long nowMillis = new Date().getTime();

        if (nowMillis >= (expirationMillis - jwtRenewIfExpiresIn) && nowMillis < expirationMillis) {
            Date newExpiration = new Date(nowMillis + jwtMsToAddAtRenew);
            return Jwts.builder()
                    .setSubject(claims.getId())
                    .setIssuedAt(claims.getIssuedAt())
                    .setExpiration(newExpiration)
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
        }

        return null;

    }
}
