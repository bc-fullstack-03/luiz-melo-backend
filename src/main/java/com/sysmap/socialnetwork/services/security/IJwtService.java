package com.sysmap.socialnetwork.services.security;

public interface IJwtService {

	String generatedToken(String email);

	boolean isValidToken(String token, String userId);

}
