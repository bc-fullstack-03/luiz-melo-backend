package com.sysmap.socialnetwork.services.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.mongodb.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService implements IJwtService {

	private final long EXPIRATION_TIME = 7200000;
	private final String KEY = "25432646294A404E635266556A586E3272357538782F413F4428472D4B615064";

	public String generatedToken(String userId) {
		return Jwts.builder().setSubject(userId).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(genSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	public boolean isValidToken(String token, String userId) {
		var sub = getClaim(token, Claims::getSubject);
		var tExpiration = getClaim(token, Claims::getExpiration);
		
		if(!sub.equals(userId) && tExpiration.before(new Date())) {
			return false;
		} 
		
		return true;
	}

	private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		var claims = Jwts.parserBuilder()
				.setSigningKey(genSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();

		return claimsResolver.apply(claims);
	}

	private Key genSignInKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
	}

}
