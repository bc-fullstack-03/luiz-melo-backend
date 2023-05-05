package com.sysmap.socialnetwork.services.user;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sysmap.socialnetwork.model.user.User;
import com.sysmap.socialnetwork.services.user.request.CreateUserRequest;
import com.sysmap.socialnetwork.services.user.request.UpdateUserRequest;

public interface IUserService {
	Page<User> findAllUser(Pageable pageable);

	User findUserById(UUID id);
	
	User findUserByEmail(String email);

	void createUser(CreateUserRequest request);

	UpdateUserRequest updateUser(UUID id, UpdateUserRequest request);
	
	void deleteUserById(UUID id);
	
	
}	
