package com.sysmap.socialnetwork.services.security;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sysmap.socialnetwork.services.user.IUserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private IJwtService jwtService;

	@Autowired
	private IUserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().endsWith("/users")) {
			filterChain.doFilter(request, response);
			return;
		}

		if (request.getServletPath().contains("/login")) {
			filterChain.doFilter(request, response);
			return;
		}

		if (request.getServletPath().contains("swagger") || request.getServletPath().contains("docs")) {
			filterChain.doFilter(request, response);
			return;
		}

		var token = request.getHeader("Authorization");
		var userId = request.getHeader("RequestedBy");

		if (token == null || userId == null || !token.startsWith("Bearer ")) {
			response.getWriter().write("Usuário não autenticado!");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}

		boolean isValidToken;

		try {
			isValidToken = jwtService.isValidToken(token.substring(7), userId);
		} catch (Exception e) {
			response.getWriter().write(e.getMessage());
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}

		if (isValidToken) {
			try {
				var user = userService.findUserById(UUID.fromString(userId));

				var authentication = new UsernamePasswordAuthenticationToken(user, null, null);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				response.getWriter().write(e.getMessage());
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				return;
			}
		} else {
			response.getWriter().write("Token inválido!");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}

		filterChain.doFilter(request, response);
	}
}
