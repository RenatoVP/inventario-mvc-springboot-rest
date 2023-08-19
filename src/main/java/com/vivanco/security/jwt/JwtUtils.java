package com.vivanco.security.jwt;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${vivanco.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${vivanco.app.jwtExpirationMs}")
	private long jwtExpirationMs;
	
	public String getUsernameInJWT(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	public boolean validateTokenJWT(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Token JWT invalido: {}" + e.getMessage());
		} catch(ExpiredJwtException e) {
			logger.error("Token JWT ha expirado: {}" + e.getMessage());
		} catch(UnsupportedJwtException e) {
			logger.error("Token JWT no es soportado: {}" + e.getMessage());
		} catch(IllegalArgumentException e) {
			logger.error("Cadena Jwt esta vacio: {}" + e.getMessage());
		}
		
		return false;
	}
	
	public String generateTokenFromUsername(String username) {
		Instant fechaActual = Instant.now();
		Instant fechaExpiracion = fechaActual.plus(jwtExpirationMs, ChronoUnit.MILLIS);
		
		Date fechaActualDate = Date.from(fechaActual);
		Date fechaExpiracionDate = Date.from(fechaExpiracion);
		
		return Jwts.builder()
					.setSubject(username)
					.setIssuedAt(fechaActualDate)
					.setExpiration(fechaExpiracionDate)
					.signWith(key(), SignatureAlgorithm.HS256)
					.compact();
	}
	
}
