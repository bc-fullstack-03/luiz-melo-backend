package com.sysmap.socialnetwork.services.post;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sysmap.socialnetwork.model.post.Post;
import com.sysmap.socialnetwork.services.post.request.InsertPostRequest;

public interface IPostService {

	List<Post> findPostByUserId(UUID id);

	Page<Post> findaAllPosts(Pageable pageable);

	Post findPostById(UUID id);
	
	public void insertPost(InsertPostRequest request);
	
}
