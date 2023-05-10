package com.sysmap.socialnetwork.models.post;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Like {
	
	private UUID userId;
	private String userName;
	
	public Like(Author author) {
		userId = author.getUserId();
		userName = author.getName();
	}
	
}
