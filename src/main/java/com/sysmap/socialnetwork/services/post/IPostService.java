package com.sysmap.socialnetwork.services.post;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sysmap.socialnetwork.models.post.Like;
import com.sysmap.socialnetwork.models.post.Post;
import com.sysmap.socialnetwork.services.post.request.InsertCommentRequest;
import com.sysmap.socialnetwork.services.post.request.InsertPostRequest;

public interface IPostService {

	Page<Post> feed(Pageable pageable);

	List<Post> findPostByUserId(UUID id);

	Post findPostById(UUID id);

	public void insertPost(InsertPostRequest request);

	void insertComment(UUID id, InsertCommentRequest request);

	void insertLikeInThePost(UUID postId, Like request);
}
