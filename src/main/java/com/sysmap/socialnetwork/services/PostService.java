package com.sysmap.socialnetwork.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysmap.socialnetwork.model.Post;
import com.sysmap.socialnetwork.repositories.PostRepository;
import com.sysmap.socialnetwork.services.exception.NotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	@Transactional
	public List<Post> findPostByUserId(UUID id) {
			 List<Post> post = repository.findPostByUserId(id);
			 if(post.isEmpty()) {
				 throw new NotFoundException("Id not found");
			 } else {
				 return post;
			 }
	}
}
