package com.sysmap.socialnetwork.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysmap.socialnetwork.model.User;
import com.sysmap.socialnetwork.repositories.UserRepository;
import com.sysmap.socialnetwork.services.exception.NotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public Page<User> findAllPaged(Pageable pageable){
		return repository.findAll(pageable);
	}
	 
	@Transactional(readOnly = true)
	public User findById(UUID id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new NotFoundException("Id not found"));
	}
	
}
