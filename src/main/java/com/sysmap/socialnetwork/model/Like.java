package com.sysmap.socialnetwork.model;

import java.util.UUID;

import com.sysmap.socialnetwork.model.dto.AuthorDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Like {
	
	private UUID userId;
	private String userName;
	
	public Like(AuthorDTO author) {
		userId = author.getUserId();
		userName = author.getName();
	}
	
}
