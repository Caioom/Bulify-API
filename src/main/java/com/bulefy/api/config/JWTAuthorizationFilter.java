package com.bulefy.api.config;

import static com.bulefy.api.config.SecurityConstants.HEADER_STRING;
import static com.bulefy.api.config.SecurityConstants.SECRET;
import static com.bulefy.api.config.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.bulefy.api.services.impl.CustomDetailServiceImpl;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	private final CustomDetailServiceImpl customDetailService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, CustomDetailServiceImpl customDetailService) {
		super(authenticationManager);
		this.customDetailService = customDetailService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header  = request.getHeader(HEADER_STRING);
		if(header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if(token == null) return null;
		
		String email = Jwts.parser().setSigningKey(SECRET)
									.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
									.getBody()
									.getSubject();
		
		UserDetails userDetails = customDetailService.loadUserByUsername(email);
		return email != null ? new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities()) : null;
	}

}
