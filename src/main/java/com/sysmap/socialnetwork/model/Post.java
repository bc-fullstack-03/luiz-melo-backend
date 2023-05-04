package com.sysmap.socialnetwork.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Document
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Setter(AccessLevel.NONE)
	private UUID id;
	private LocalDateTime date;
	private String content;
	private UUID userId;
	private String userName;

	@Setter(AccessLevel.NONE)
	private List<Comment> comments = new ArrayList<>();
	
	@Setter(AccessLevel.NONE)	
	private Set<Like> likes = new HashSet<>();
	
	public Post(String content, Author user) {
		super();
		id = UUID.randomUUID();
		date = LocalDateTime.now();
		userId = user.getUserId();
		userName = user.getName();
		this.content = content;
	}

}