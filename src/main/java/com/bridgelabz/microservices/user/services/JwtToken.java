package com.bridgelabz.microservices.user.services;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtToken {

	@Value("${Key}")
	private String KEY;

	// Generate token for string
	public String tokenGenerator(String email) {
		String passkey = "verify";
		long nowMillis = System.currentTimeMillis() + (20 * 60 * 60 * 1000);
		Date now = new Date(nowMillis);
		JwtBuilder jwtBuilder = Jwts.builder().setId(passkey).setIssuedAt(now).setSubject(email)
				.signWith(SignatureAlgorithm.HS256, KEY);
		return jwtBuilder.compact();
	}

	// Used for parsing the token
	public String parseJwtToken(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY)).parseClaimsJws(jwt)
				.getBody();
		System.out.println("Subject : " + claims.getSubject());
		System.out.println("ID : " + claims.getId());
		return claims.getSubject();
	}
}
