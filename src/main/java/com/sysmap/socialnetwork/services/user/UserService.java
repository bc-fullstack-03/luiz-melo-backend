package com.sysmap.socialnetwork.services.user;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sysmap.socialnetwork.models.user.Follow;
import com.sysmap.socialnetwork.models.user.User;
import com.sysmap.socialnetwork.repositories.UserRepository;
import com.sysmap.socialnetwork.services.exception.ArgumentNotValidException;
import com.sysmap.socialnetwork.services.exception.NotFoundException;
import com.sysmap.socialnetwork.services.fileupload.IFileUploadService;
import com.sysmap.socialnetwork.services.user.request.CreateUserRequest;
import com.sysmap.socialnetwork.services.user.request.UpdateUserRequest;
import com.sysmap.socialnetwork.services.user.response.FindOneUserResponse;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IFileUploadService fileUplocadService;

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

	@Transactional
	public void uploadPhotoProfile(@RequestParam("photo") MultipartFile photo) throws Exception {
		var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		var photoUri = "";

		try {
			var fileName = user.getId() + "."
					+ photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);

			photoUri = fileUplocadService.upload(photo, fileName);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		user.setPhotoUri(photoUri);
		userRepository.save(user);

	}
	
	@Transactional
	public void follow(UUID myId, UUID userId) {
		var user = findUserById(myId);
		var follow = findUserById(userId);
		var following = new Follow(follow);
		user.getFollowing().add(following);
		follow.getFollowers().add(new Follow(user));
		userRepository.saveAll(Arrays.asList(user,follow));
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
