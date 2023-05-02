package com.sysmap.socialnetwork.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
