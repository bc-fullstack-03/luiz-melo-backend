package com.sysmap.socialnetwork.services.user.response;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.sysmap.socialnetwork.models.user.Follow;
import com.sysmap.socialnetwork.models.user.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindOneUserResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Setter(AccessLevel.NONE)
	private UUID id;
	private String name;
	private String email;
	private String photoUri;
 
	
	@Setter(AccessLevel.NONE)
	private Set<Follow> followers = new HashSet<>();

	@Setter(AccessLevel.NONE)
	private Set<Follow> following = new HashSet<>();

	public FindOneUserResponse(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		photoUri = entity.getPhotoUri();
		followers = entity.getFollowers();
		following = entity.getFollowing();
	}

}
