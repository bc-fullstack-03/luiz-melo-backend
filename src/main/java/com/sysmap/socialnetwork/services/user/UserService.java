package com.sysmap.socialnetwork.services.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysmap.socialnetwork.model.user.User;
import com.sysmap.socialnetwork.repositories.UserRepository;
import com.sysmap.socialnetwork.services.exception.NotFoundException;
import com.sysmap.socialnetwork.services.user.request.CreateUserRequest;
import com.sysmap.socialnetwork.services.user.request.UpdateUserRequest;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository repository;

	@Transactional(readOnly = true)
	public Page<User> findAllUser(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public User findUserById(UUID id) {
		return repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Id not found"));
	}
	
	@Transactional(readOnly = true)
	public User findUserByEmail(String email) {
		return repository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Id not found"));	
	}
	
	@Transactional
	public void createUser(CreateUserRequest request) {
		var user = new User(request.getName(), request.getEmail(), request.getEmail());
		repository.save(user);
	}

	@Transactional
	public UpdateUserRequest updateUser(UUID id, UpdateUserRequest request) {
		var user = findUserById(id);
		mappingRequestToModel(request, user);
		user = repository.save(user);
		return new UpdateUserRequest(user);
	}

	@Transactional
	public void deleteUserById(UUID id) {
		findUserById(id);
		repository.deleteById(id);
	}

	public void mappingRequestToModel(UpdateUserRequest request, User user) {
		if (request.getName() != null) {
			user.setName(request.getName());
		}
		if (request.getPassword() != null) {
			user.setPassword(request.getPassword());
		}

		if (request.getPhotoUri() != null) {
			user.setPhotoUri(request.getPhotoUri());
		}
	}

}
