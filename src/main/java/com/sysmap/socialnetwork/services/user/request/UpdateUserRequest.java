package com.sysmap.socialnetwork.services.user.request;

import java.io.Serializable;

import com.sysmap.socialnetwork.model.user.User;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Size(min = 2, max = 32, message = "Este deverá conter no minimo 2 caracteres e no máximo 32")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Apenas letras maiúsculas e minúsculas")
	private String name;
	
	@Size(min = 8, max =32, message = "A senha deverá conter no minimo 8 caracteres e 32 no máximo" )
	private String password;
	
	private String photoUri;
	

	public UpdateUserRequest(User entity) {
		name = entity.getName();
		password = entity.getPassword();
		photoUri = entity.getPhotoUri();
	}

}
