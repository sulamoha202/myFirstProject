package com.coderscamp.AssignemetSubmission.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscamp.AssignemetSubmission.domain.User;
import com.coderscamp.AssignemetSubmission.dto.AuthCredentialsRequest;
import com.coderscamp.AssignemetSubmission.util.JwtUtil;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private  AuthenticationManager authenticationManagerr ;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody AuthCredentialsRequest request){
		  try {
	            Authentication authenticate = authenticationManagerr
	                .authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                        request.getUsername(), request.getPassword()
	                    )
	                );
	            
	            User user = (User) authenticate.getPrincipal();
	            user.setPassword(null);
	            //System.out.println(request.getUsername()+" : "+request.getPassword());
	            return ResponseEntity.ok()
	                .header(
	                    HttpHeaders.AUTHORIZATION,
	                    jwtUtil.generateToken(user)
	                )
	                .body(user);
	        } catch (BadCredentialsException ex) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
	}
}
