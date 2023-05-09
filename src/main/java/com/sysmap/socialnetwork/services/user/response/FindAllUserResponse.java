package com.sysmap.socialnetwork.services.user.response;

import java.io.Serializable;
import java.util.UUID;

import com.sysmap.socialnetwork.models.user.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindAllUserResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Setter(AccessLevel.NONE)
	private UUID id;
	private String name;
	private String email;
	private String photoUri;
	
	public FindAllUserResponse(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		photoUri = entity.getPhotoUri();
	}

}
