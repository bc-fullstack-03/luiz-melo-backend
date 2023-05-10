package com.sysmap.socialnetwork.services.post;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysmap.socialnetwork.models.post.Author;
import com.sysmap.socialnetwork.models.post.Comment;
import com.sysmap.socialnetwork.models.post.Like;
import com.sysmap.socialnetwork.models.post.Post;
import com.sysmap.socialnetwork.repositories.PostRepository;
import com.sysmap.socialnetwork.services.exception.NotFoundException;
import com.sysmap.socialnetwork.services.post.request.InsertCommentRequest;
import com.sysmap.socialnetwork.services.post.request.InsertPostRequest;
import com.sysmap.socialnetwork.services.user.IUserService;

@Service
public class PostService implements IPostService{

	@Autowired
	private PostRepository repository;
	
	@Autowired
	private IUserService userService;
	
	@Transactional(readOnly = true)
	public Page<Post> feed(Pageable pageable){
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
		var author = new Author(userService.findUserById(request.getUserId()));
		var post = new Post(request.getContent(), author.getUserId(),author.getName());
		repository.save(post);
	}
	
	@Transactional
	public void deletePost(UUID id) {
		findPostById(id);
		repository.deleteById(id);
	}
	
	@Transactional
	public void insertLikeInThePost(UUID postId, Like request) {
		var author = new Author(userService.findUserById(request.getUserId()));
		var post = findPostById(postId);
		post.getLikes().add(new Like(author));
		repository.save(post);
	}
	
	@Transactional
	public void insertComment(UUID postId, InsertCommentRequest request) {
		var author = new Author(userService.findUserById(request.getAuthorId()));
		var post = findPostById(postId);
		post.getComments().add(new Comment(request.getContent(), author));
		repository.save(post);
	}
	
}
