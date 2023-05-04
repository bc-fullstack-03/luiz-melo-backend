package com.sysmap.socialnetwork.model.user.responses;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import com.sysmap.socialnetwork.model.Follower;
import com.sysmap.socialnetwork.model.user.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Setter(AccessLevel.NONE)
	private UUID id;
	private String name;
	private String photoUri;
	
	@Setter(AccessLevel.NONE)
	private Set<Follower> follower;

	public UserResponse(User entity) {
		id = entity.getId();
		name = entity.getName();
		photoUri = entity.getPhotoUri();
		follower = entity.getFollowers();
	}

}
