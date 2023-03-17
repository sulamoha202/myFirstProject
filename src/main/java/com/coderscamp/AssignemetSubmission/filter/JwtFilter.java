package com.coderscamp.AssignemetSubmission.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.coderscamp.AssignemetSubmission.repository.UserRepository;
import com.coderscamp.AssignemetSubmission.util.JwtUtil;


@Component	
public class JwtFilter extends OncePerRequestFilter{
		
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(header) || (StringUtils.hasText(header) && !header.startsWith("Bearer "))) {
            chain.doFilter(request, response);
            return;
        }
        
        //Authorization -> Bearer asdf.138974sh.alsjkh123879  (garbage)
        final String token = header.split(" ")[1].trim();
        // Get user identity and set it on the spring security context
        UserDetails userDetails = userRepo
	            .findByUsername(jwtUtil.getUsernameFormToken(token))
	            .orElse(null);
        // Get jwt token and validate//if you have beare in header
        if (!jwtUtil.vaildateToken(token,userDetails)) {
            chain.doFilter(request, response);
            return;
        }
       
        UsernamePasswordAuthenticationToken
            authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                    List.of() : userDetails.getAuthorities()
            );
        authentication.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
//this is where tha authentiacation magic happens and the user is now vaild!
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
	
}
