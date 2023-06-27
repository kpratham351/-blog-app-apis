package com.pratham.blog.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenHelper jwttokenHelper;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestToken = request.getHeader("Authorization");
		
		String username = null;
		String  token = null;
		
		if(request!= null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			try {
				username = this.jwttokenHelper.getUserNameFromToken(token);
			}
			catch(IllegalArgumentException e) {
				System.out.println("Unable to get Jwt token");
			}catch(ExpiredJwtException e) {
				System.out.println("Jwt token Expired");
			}catch(MalformedJwtException e) {
				System.out.println("Jwt token does not bigen with Bearer");
			}
		}else {
			System.out.println("Jwt token does not begin with bearer");
		}
		if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null ) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(this.jwttokenHelper.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().getAuthentication();
			}else {
				System.out.println("Invalid token");
			}
		}else {
			System.out.println("Username is null or Context is not null");
		}
		filterChain.doFilter(request,response);
	}

}
