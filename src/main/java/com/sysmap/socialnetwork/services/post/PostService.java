package com.sysmap.socialnetwork.services.post;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysmap.socialnetwork.model.post.Post;
import com.sysmap.socialnetwork.repositories.PostRepository;
import com.sysmap.socialnetwork.services.exception.NotFoundException;
import com.sysmap.socialnetwork.services.post.request.InsertPostRequest;

@Service
public class PostService implements IPostService{

	@Autowired
	private PostRepository repository;

	@Transactional(readOnly = true)
	public Page<Post> findaAllPosts(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public List<Post> findPostByUserId(UUID id) {
			 List<Post> post = repository.findByUserId(id);
			 if(post.isEmpty()) {
				 throw new NotFoundException("Resource not found");
			 } else {
				 return post;
			 }
	}
	
	@Transactional(readOnly = true)
	public Post findPostById(UUID id) {
		return repository.findById(id)
				.orElseThrow(() -> new NotFoundException("id not found"));
	}
	
	@Transactional
	public void insertPost(InsertPostRequest request) {
		var post = new Post(request.getContent(), request.getUserId(),request.getUserName());
		repository.save(post);
	}
	
	@Transactional
	public void deletePost(UUID id) {
		findPostById(id);
		repository.deleteById(id);
	}
	
}
