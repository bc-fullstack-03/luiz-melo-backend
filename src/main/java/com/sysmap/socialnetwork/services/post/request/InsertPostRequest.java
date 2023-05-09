package com.sysmap.socialnetwork.services.post.request;

import java.util.UUID;

import com.sysmap.socialnetwork.models.post.Author;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InsertPostRequest {

	private UUID userId;
	private String content;

	public InsertPostRequest(String content, Author entity) {
		userId = entity.getUserId();
		entity.getName();
		this.content = content;
	}

}
