package com.sysmap.socialnetwork.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysmap.socialnetwork.services.authentication.AuthenticateRequest;
import com.sysmap.socialnetwork.services.authentication.AuthenticateResponse;
import com.sysmap.socialnetwork.services.authentication.IAuthenticationService;

@RestController
@RequestMapping(value = "api/v1/login")
public class AuthenticationResource {
	
	@Autowired
	private IAuthenticationService authenticationService;
	
	
	@PostMapping
	public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request){
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
}
