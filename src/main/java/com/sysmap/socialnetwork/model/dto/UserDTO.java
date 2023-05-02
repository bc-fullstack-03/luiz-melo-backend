package com.sysmap.socialnetwork.model.dto;

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
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Setter(AccessLevel.NONE)
	private UUID id;
	private String name;
	private String email;
	private String password;
	private String profilePictureUri;

	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		password = entity.getPassword();
		profilePictureUri = entity.getProfilePictureUri();
	}

}
