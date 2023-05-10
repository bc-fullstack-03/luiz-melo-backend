package com.sysmap.socialnetwork.services.post.request;

import java.util.UUID;

import com.sysmap.socialnetwork.models.post.Author;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InsertCommentRequest {
	
	private String content;
	private UUID authorId;
	private String authorName;
	
	
	public InsertCommentRequest(String content, Author entity) {
		authorId = entity.getUserId();
		entity.getName();
		this.content = content;
	}
	
}
