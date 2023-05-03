package com.sysmap.socialnetwork.model.dto;

import java.util.UUID;

import com.sysmap.socialnetwork.model.User;

import lombok.Data;

@Data
public class AuthorDTO {
	
	private UUID userId;
	private String name;
	
	public AuthorDTO(User user) {
		userId = user.getId();
		name = user.getName();
	}
	
}
