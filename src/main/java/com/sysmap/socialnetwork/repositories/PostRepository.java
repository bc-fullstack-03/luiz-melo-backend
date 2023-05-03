package com.sysmap.socialnetwork.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sysmap.socialnetwork.model.Post;

public interface PostRepository  extends MongoRepository<Post, UUID>{
	
	List<Post> findPostByUserId(UUID userId);
	
}
