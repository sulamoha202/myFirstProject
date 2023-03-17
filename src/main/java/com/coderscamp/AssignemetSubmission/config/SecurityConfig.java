package com.coderscamp.AssignemetSubmission.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.coderscamp.AssignemetSubmission.filter.JwtFilter;
import com.coderscamp.AssignemetSubmission.util.CustomPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;	
	@Autowired 
	private CustomPasswordEncoder customPasswordEncoder;
	//our filter
	@Autowired
	private JwtFilter jwtFilter;
	
	
	@Override @Bean
	public AuthenticationManager authenticationManagerBean ()throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(customPasswordEncoder.getPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http = http.cors().and().csrf().disable();
		
		http = http
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and();
		
		http = http
				.exceptionHandling()
				.authenticationEntryPoint(
						(request,response,ex)->{
							response.sendError(HttpServletResponse.SC_UNAUTHORIZED,ex.getMessage()
							);
						}
					)
				.and();
		http.authorizeRequests()
			.antMatchers("/api/auth/**").permitAll()
			.anyRequest()
			.authenticated();
			
		http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
						
	}
	
	
}
