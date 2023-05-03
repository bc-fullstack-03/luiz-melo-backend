package com.sysmap.socialnetwork.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysmap.socialnetwork.model.Post;
import com.sysmap.socialnetwork.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<List<Post>> findPostByUserId(@PathVariable UUID id){
		var post = service.findPostByUserId(id);
		return ResponseEntity.ok(post);
	}
}
