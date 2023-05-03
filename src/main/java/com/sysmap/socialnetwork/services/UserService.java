package com.sysmap.socialnetwork.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysmap.socialnetwork.model.User;
import com.sysmap.socialnetwork.model.dto.UserDTO;
import com.sysmap.socialnetwork.repositories.UserRepository;
import com.sysmap.socialnetwork.services.exception.NotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Transactional(readOnly = true)
	public Page<User> findAllPaged(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public User findById(UUID id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new NotFoundException("Id not found"));
	}

	@Transactional
	public User insert(UserDTO request) {
		User user = new User();
		BeanUtils.copyProperties(request, user);
		return repository.save(user);
	}

	@Transactional
	public UserDTO update(UUID id, UserDTO request) {
		User user = repository.findById(id).get();
		convertDtoToModel(request, user);
		// var user = convertDtoToModel(userDTO, userOptional.get());
		user = repository.save(user);
		return new UserDTO(user);
	}

	@Transactional
	public void delete(UUID id) {
		try {
			repository.deleteById(id);
		} catch (NotFoundException e) {
			throw new NotFoundException("id not found" + id);
		}
	}

	public void convertDtoToModel(UserDTO request, User user) {
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setProfilePictureUri(request.getProfilePictureUri());
	}

}
