package com.sysmap.socialnetwork.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sysmap.socialnetwork.models.post.Post;
import com.sysmap.socialnetwork.services.post.PostService;
import com.sysmap.socialnetwork.services.post.request.InsertPostRequest;
import com.sysmap.socialnetwork.services.post.response.GetAllPostResponse;
import com.sysmap.socialnetwork.services.post.response.GetOnePostResponse;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	
	@GetMapping
	public ResponseEntity<Page<GetAllPostResponse>> findAllPost(Pageable pageable) {
		Page<Post> post = service.findaAllPosts(pageable);
		var pageResponse = post.map(GetAllPostResponse::new);
		return ResponseEntity.ok(pageResponse);
	}

	@GetMapping(value = "/user/{id}")
	public ResponseEntity<List<GetAllPostResponse>> findPostByUserId(@PathVariable UUID id) {
		List<Post> post = service.findPostByUserId(id);
		List<GetAllPostResponse> list = post.stream().map(GetAllPostResponse::new).toList();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<GetOnePostResponse> findPostById(@PathVariable UUID id) {
		Post post = service.findPostById(id);
		return ResponseEntity.ok(new GetOnePostResponse(post));
	}

	@PostMapping
	public void insertPost(@RequestBody InsertPostRequest request) {		
		service.insertPost(request);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(request).toUri();
		ResponseEntity.created(uri).build();
	}


	@DeleteMapping(value = "/{id}")
	public void deletePost(@PathVariable UUID id) {
		service.deletePost(id);
		ResponseEntity.noContent().build();
	}

}
