package com.sysmap.socialnetwork.resources;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysmap.socialnetwork.model.User;
import com.sysmap.socialnetwork.model.response.UserResponse;
import com.sysmap.socialnetwork.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable){
		Page<User> page = service.findAllPaged(pageable);
		var pageResponse = page.map(x -> new UserResponse(x));
		return ResponseEntity.ok(pageResponse);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponse> findById(@PathVariable UUID id){
		var user = service.findById(id);
		return ResponseEntity.ok(new UserResponse(user));
	}
	
	
	
}
