package com.tywdi.backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.service
 * Date: 31.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Service
@PropertySource("classpath:application.properties")
public class JwtTokenService implements JwtTokenServiceInterface {

    private final String secret;

    private final int expirationInHours;

    public JwtTokenService(@Value("${jwt.secret}") final String secret,
                           @Value("${jwt.expiration}") final int expirationInHours) {
        this.secret = secret;
        this.expirationInHours = expirationInHours;
    }

    @Override
    public String getUsernameFromToken(final String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    @Override
    public Date getExpirationDateFromToken(final String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    @Override
    public <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Claims getAllClaimsFromToken(final String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String generateToken(final String email) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate();

        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(email)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @Override
    public Date localDateTimeToDate(final LocalDateTime localDateTime) {
        return java.sql.Timestamp.valueOf(localDateTime);
    }

    @Override
    public Boolean isTokenNotExpired(final String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.after(new Date());
    }

    @Override
    public Optional<Boolean> validateToken(final String token) {
        return isTokenNotExpired(token) ? Optional.of(Boolean.TRUE) : Optional.empty();
    }

    @Override
    public Date calculateExpirationDate() {
        return localDateTimeToDate(LocalDateTime
                .now()
                .plusHours(expirationInHours));
    }
}
