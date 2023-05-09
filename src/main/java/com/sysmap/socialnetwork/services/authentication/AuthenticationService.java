package com.sysmap.socialnetwork.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sysmap.socialnetwork.services.exception.NotFoundException;
import com.sysmap.socialnetwork.services.security.IJwtService;
import com.sysmap.socialnetwork.services.user.IUserService;

@Service
public class AuthenticationService implements IAuthenticationService {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IJwtService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AuthenticateResponse authenticate(AuthenticateRequest request) {
		var user = userService.getUser(request.getEmail());
		
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new NotFoundException("Acesso negado, email e senha não coincidem");
		}
		
		var token = jwtService.generatedToken(user.getId().toString());
		
		return new AuthenticateResponse(user.getId(), token);
	}
}
