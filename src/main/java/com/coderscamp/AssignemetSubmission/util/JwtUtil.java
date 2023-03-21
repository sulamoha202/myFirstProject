package com.coderscamp.AssignemetSubmission.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil implements Serializable{
	private static final long serialVersionUID=-2550185165626007488L;
	public static final long JWT_TOKEN_VALIDITY = 2592000; //this number is in seconds equals to 30*24 *60*60 one Month;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String getUsernameFormToken(String token) {
		
		return getClaimFromToken(token,Claims::getSubject);
		}
	//jwt expiration
	public Date getIssuedAtDateFormToken(String token) {
		return   getClaimFromToken(token,Claims::getIssuedAt);
		
	}//jwt expiration
	public Date getExpirationDateFromToken(String token) {
		return  getClaimFromToken(token,Claims::getExpiration);
		
	}
	public<T> T getClaimFromToken(String token,Function<Claims,T>claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	//jwt expiration
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	//jwt expiration
	private Boolean isTokenExpired(String token)
	{final Date expiration =getExpirationDateFromToken(token);
	return expiration.before(new Date());
	}
	private Boolean ignoreTokenExpiration(String Token) {
		//here yoy specify tokens,for that the expiration is ignored
		return false;
	}
	public String generateToken(UserDetails userDetails) {
		Map<String,Object>claims=new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
		}
	private String doGenerateToken(Map<String,Object>claims,String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date (System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512,secret).compact();
		
	}
	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token)||ignoreTokenExpiration(token));
	}
	//jwt expiration
	public Boolean vaildateToken(String token,UserDetails userDetails) {
		final String username=getUsernameFormToken(token);
		return (userDetails !=null && username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}