package com.sysmap.socialnetwork.model.user;

import java.io.Serializable;
import java.util.Set;

import com.sysmap.socialnetwork.model.Follower;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest implements Serializable {
	private static final long serialVersionUID = 1L;


	private String name;
	private String email;
	private String password;
	private String profilePictureUri;
	
	private Set<Follower> follower;

	public UserRequest(User entity) {
		name = entity.getName();
		email = entity.getEmail();
		password = entity.getPassword();
		profilePictureUri = entity.getProfilePictureUri();
		follower = entity.getFollowers();
	}

}
