package com.sysmap.socialnetwork.services.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysmap.socialnetwork.models.user.User;
import com.sysmap.socialnetwork.repositories.UserRepository;
import com.sysmap.socialnetwork.services.exception.ArgumentNotValidException;
import com.sysmap.socialnetwork.services.exception.NotFoundException;
import com.sysmap.socialnetwork.services.user.request.CreateUserRequest;
import com.sysmap.socialnetwork.services.user.request.UpdateUserRequest;
import com.sysmap.socialnetwork.services.user.response.FindOneUserResponse;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public Page<User> findAllUser(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public User findUserById(UUID id) {
		return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
	}

	@Transactional(readOnly = true)
	public FindOneUserResponse findUserByEmail(String email) {
		var user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Email não encontrado"));
		return new FindOneUserResponse(user);

	}

	public User getUser(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Email not found: " + email));
	}

	@Transactional
	public void createUser(CreateUserRequest request) {
		Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
		if (userOptional.isPresent()) {
			throw new ArgumentNotValidException("Email já cadastrado");
		}
		var user = new User(request.getName(), request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		userRepository.save(user);
	}

	@Transactional
	public UpdateUserRequest updateUser(UUID id, UpdateUserRequest request) {
		var user = findUserById(id);
		mappingRequestToModel(request, user);
		user = userRepository.save(user);
		return new UpdateUserRequest(user);
	}

	@Transactional
	public void deleteUserById(UUID id) {
		findUserById(id);
		userRepository.deleteById(id);
	}

	public void mappingRequestToModel(UpdateUserRequest request, User user) {
		if (request.getName() != null) {
			user.setName(request.getName());
		}
		if (request.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(request.getPassword()));
		}

		if (request.getPhotoUri() != null) {
			user.setPhotoUri(request.getPhotoUri());
		}
	}
}
