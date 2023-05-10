package com.sysmap.socialnetwork.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sysmap.socialnetwork.models.post.Comment;
import com.sysmap.socialnetwork.models.post.Post;

public interface PostRepository  extends MongoRepository<Post, UUID>{
	
	List<Post> findByUserId(UUID id);
	
	Comment findCommentById(UUID id);

	void save(Comment comment);
}
