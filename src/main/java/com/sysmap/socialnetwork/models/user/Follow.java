package com.sysmap.socialnetwork.models.user;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Follow {
	
	private UUID userId;
	private String username;
	
	public Follow(UUID userId) {
		this.userId = userId;
	}
	
	public Follow(User user) {
		userId = user.getId();
		username = user.getName();
	}
	
}
