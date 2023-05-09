package com.sysmap.socialnetwork.models.user;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Follower {
	
	private UUID userId;
	private String username;
	
	
	public Follower(User user) {
		userId = user.getId();
		username = user.getName();
	}
	
}
