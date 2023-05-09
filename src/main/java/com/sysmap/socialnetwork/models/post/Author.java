package com.sysmap.socialnetwork.models.post;

import java.util.UUID;

import com.sysmap.socialnetwork.models.user.User;

import lombok.Data;

@Data
public class Author {
	
	private UUID userId;
	private String name;
	
	public Author(User user) {
		userId = user.getId();
		name = user.getName();
	}
	
}
