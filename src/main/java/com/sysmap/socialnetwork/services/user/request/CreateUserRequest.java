package com.sysmap.socialnetwork.services.user.request;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Campo obrigatório")
	@Size(min = 2, max = 32)
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Apenas letras maiúsculas e minúsculas")
	private String name;
	
	@Email
	@NotBlank(message = "Campo obrigatório")
	private String email;
	
	@NotBlank(message = "Campo obrigatório")
	@Size(min = 8, max =32 )
	private String password;
}
