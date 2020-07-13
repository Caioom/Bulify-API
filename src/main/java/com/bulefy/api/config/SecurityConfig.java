package com.bulefy.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import com.bulefy.api.services.impl.CustomDetailServiceImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomDetailServiceImpl customDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(request -> this.corsConfiguration())
		.and().csrf().disable()
		.authorizeRequests()
		.antMatchers("/*/user/**").hasRole("USUARIO")
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager()))
		.addFilter(new JWTAuthorizationFilter(authenticationManager(), customDetailService));
	}
	
	private CorsConfiguration corsConfiguration() {
		CorsConfiguration cors = new CorsConfiguration();
		cors.setAllowedHeaders(Arrays.asList("*"));
		cors.setAllowedMethods(Arrays.asList("*"));
		cors.setAllowedOrigins(Arrays.asList("*"));
		return cors;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customDetailService).passwordEncoder(new BCryptPasswordEncoder());		
	}
}
