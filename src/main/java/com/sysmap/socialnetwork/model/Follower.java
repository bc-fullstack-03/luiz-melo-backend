package com.sysmap.socialnetwork.model;

import java.util.UUID;

import com.sysmap.socialnetwork.model.user.User;

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
