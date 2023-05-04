package com.sysmap.socialnetwork.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private String content;
	private LocalDateTime date;
	private UUID authorId;
	private String authorName;
	
	private Set<Like> likes = new HashSet<>();
	
	public Comment(String content, Author author) {
		this.content = content;
		date = LocalDateTime.now();
		authorId = author.getUserId();
		authorName = author.getName();
	}

}
