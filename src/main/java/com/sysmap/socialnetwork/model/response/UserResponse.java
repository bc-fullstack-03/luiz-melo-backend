package com.sysmap.socialnetwork.model.response;

import java.io.Serializable;
import java.util.UUID;

import com.sysmap.socialnetwork.model.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Setter(AccessLevel.NONE)
	private UUID id;
	private String name;
	private String profilePictureUri;
	
	public   UserResponse (User entity) {
		id = entity.getId();
		name = entity.getName();
		profilePictureUri = entity.getProfilePictureUri();
	}
	
}
