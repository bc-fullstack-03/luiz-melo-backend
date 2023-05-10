package com.sysmap.socialnetwork.models.user;

import java.io.Serializable;
import java.util.HashSet;
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
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Setter(AccessLevel.NONE)
	private UUID id;
	private String name;
	private String email;
	private String password; 
	private String photoUri;
	
	@Setter(AccessLevel.NONE)
	private Set<Follow> followers = new HashSet<>();

	@Setter(AccessLevel.NONE)
	private Set<Follow> following = new HashSet<>();
	
	public User(String name, String email) {
		id = UUID.randomUUID();
		this.name = name;
		this.email = email;
	}

}
