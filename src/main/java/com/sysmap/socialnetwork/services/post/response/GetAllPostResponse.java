package com.sysmap.socialnetwork.services.post.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sysmap.socialnetwork.models.post.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAllPostResponse {
	
	private UUID postId;
	private String userName;
	private UUID userId;
	private String content;
	private LocalDateTime date;
	private Integer likes;
	private Integer comments;
	
	public GetAllPostResponse(Post entity) {
		postId = entity.getId();
		userName = entity.getUserName();
		userId = entity.getUserId();
		content = entity.getContent();
		date = entity.getDate();
		likes = entity.getLikes().size();
		comments = entity.getComments().size();
	}
}
