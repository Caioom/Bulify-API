package com.bulefy.api.config;

import static com.bulefy.api.config.SecurityConstants.EXPIRATION_TIME;
import static com.bulefy.api.config.SecurityConstants.HEADER_STRING;
import static com.bulefy.api.config.SecurityConstants.SECRET;
import static com.bulefy.api.config.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bulefy.api.models.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			return this.authManager
					.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String email = ((User) authResult.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
							.setSubject(email)
							.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
							.signWith(SignatureAlgorithm.HS512, SECRET)
							.compact();
		response.getWriter().write(TOKEN_PREFIX+token);
		response.addHeader(HEADER_STRING, TOKEN_PREFIX+token);
	}
}
