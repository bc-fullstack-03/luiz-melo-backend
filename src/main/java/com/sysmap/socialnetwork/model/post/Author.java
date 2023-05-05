package com.sysmap.socialnetwork.model.post;

import java.util.UUID;

import com.sysmap.socialnetwork.model.user.User;

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
