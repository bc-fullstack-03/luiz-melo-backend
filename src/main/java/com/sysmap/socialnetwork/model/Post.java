package com.sysmap.socialnetwork.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class Post implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Setter(AccessLevel.NONE)
	private UUID id;
	
	private LocalDateTime date;
	private String content;
	private UUID userId;
	
	
	public Post(LocalDateTime date, String content, UUID userId) {
		super();
		id = UUID.randomUUID();
		this.date = date;
		this.content = content;
		this.userId = userId;
	}
	
}