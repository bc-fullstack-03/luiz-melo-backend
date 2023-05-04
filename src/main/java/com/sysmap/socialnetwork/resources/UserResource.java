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
import com.sysmap.socialnetwork.model.user.UserRequest;
import com.sysmap.socialnetwork.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<Page<UserRequest>> findAll(Pageable pageable){
		Page<User> page = service.findAllPaged(pageable);
		var pageResponse = page.map(x -> new UserRequest(x));
		return ResponseEntity.ok(pageResponse);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserRequest> findById(@PathVariable UUID id){
		var user = service.findById(id);
		return ResponseEntity.ok(new UserRequest(user));
	}
	
	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody UserRequest request) {
		service.insert(request);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(request)
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody UserRequest request) {
		service.update(id, request);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
