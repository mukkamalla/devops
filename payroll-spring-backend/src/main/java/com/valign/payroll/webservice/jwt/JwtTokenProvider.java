package com.valign.payroll.webservice.jwt;


import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.valign.payroll.webservice.dto.PayrollRegisteredUsers;
import com.valign.payroll.webservice.exception.InvalidJWTToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length}")
	private long validityInMilliseconds; // 24h

	@Autowired
	private ReactiveMongoOperations operations;

	public String createToken(String username, List<Role> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority()))
				.filter(Objects::nonNull).collect(Collectors.toList()));

		Date now = new Date();
		System.out.println("token creation date is " + now.toGMTString());
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());

		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, encodedSecretKey).compact();
	}

	public Optional<Jws<Claims>> getClaims(Optional<String> token) {
		if (!token.isPresent()) {
			return Optional.empty();
		}
		String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		return Optional.of(Jwts.parser().setSigningKey(encodedSecretKey).parseClaimsJws(token.get()));
	}

	public Authentication getAuthentication(String token) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(getUsername(token)));
		PayrollRegisteredUsers user = operations.findOne(query, PayrollRegisteredUsers.class).block();

		return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
	}

	public String getUsername(String token) {
		String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		return Jwts.parser().setSigningKey(encodedSecretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public boolean validateToken(String token) throws InvalidJWTToken  {
		String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		InvalidJWTToken ijt = null;
		try {
			Jwts.parser().setSigningKey(encodedSecretKey).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			System.out.println("before printing the message 12345");
			//e.printStackTrace();
			if (e instanceof io.jsonwebtoken.MalformedJwtException) {
				System.out.println("inside malformed JWT exception");
				
			}
			if (e instanceof io.jsonwebtoken.SignatureException) {
				System.out.println("inside expired or untrusted JWT exception");
				
			}
			
			
		}
		
		return false;
	}

}
