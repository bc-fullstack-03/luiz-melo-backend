package com.sysmap.socialnetwork.model.user;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sysmap.socialnetwork.model.Follower;

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
	private Set<Follower> followers = new HashSet<>();
	
	public User(String name, String email, String password) {
		id = UUID.randomUUID();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	
	
}
