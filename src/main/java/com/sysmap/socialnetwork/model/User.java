package com.sysmap.socialnetwork.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
	private String profilePictureUri;
		
	
	public User(String name, String email, String password) {
		id = UUID.randomUUID();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	
	
}
