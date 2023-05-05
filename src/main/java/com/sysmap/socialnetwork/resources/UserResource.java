package com.sysmap.socialnetwork.resources;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sysmap.socialnetwork.model.user.User;
import com.sysmap.socialnetwork.services.user.IUserService;
import com.sysmap.socialnetwork.services.user.request.CreateUserRequest;
import com.sysmap.socialnetwork.services.user.request.UpdateUserRequest;
import com.sysmap.socialnetwork.services.user.response.FindAllUserResponse;
import com.sysmap.socialnetwork.services.user.response.FindOneUserResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private IUserService service;
	
	@GetMapping
	public ResponseEntity<Page<FindAllUserResponse>> findAllUser(Pageable pageable){
		Page<User> page = service.findAllUser(pageable);
		var pageResponse = page.map(FindAllUserResponse::new);
		return ResponseEntity.ok(pageResponse);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<FindOneUserResponse> findUserById(@PathVariable UUID id){
		var user = service.findUserById(id);
		return ResponseEntity.ok(new FindOneUserResponse(user));
	}
	
	
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<FindOneUserResponse> findUserByEmail(@PathVariable String email){
		var user = service.findUserByEmail(email);
		return ResponseEntity.ok(new FindOneUserResponse(user));
	}
	
	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserRequest request) {
		service.createUser(request);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(request)
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable UUID id, @Valid  @RequestBody UpdateUserRequest request) {
		service.updateUser(id, request);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
		service.deleteUserById(id);
		return ResponseEntity.noContent().build();
	}

}
