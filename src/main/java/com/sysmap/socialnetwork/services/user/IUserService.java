package com.sysmap.socialnetwork.services.user;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sysmap.socialnetwork.models.user.User;
import com.sysmap.socialnetwork.services.user.request.CreateUserRequest;
import com.sysmap.socialnetwork.services.user.request.UpdateUserRequest;
import com.sysmap.socialnetwork.services.user.response.FindOneUserResponse;

public interface IUserService {
	Page<User> findAllUser(Pageable pageable);

	User findUserById(UUID id);
	
	FindOneUserResponse findUserByEmail(String email);
	
	User getUser(String email);
	
	void createUser(CreateUserRequest request);

	UpdateUserRequest updateUser(UUID id, UpdateUserRequest request);
	
	void deleteUserById(UUID id);
	
	void uploadPhotoProfile(@RequestParam("photo") MultipartFile photo) throws Exception;
	
	public void follow(UUID myId, UUID userId);
	
}	
