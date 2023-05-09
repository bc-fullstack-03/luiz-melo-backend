package com.sysmap.socialnetwork.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sysmap.socialnetwork.models.user.User;

public interface UserRepository extends MongoRepository<User, UUID>{
	Optional<User> findByEmail(String email);
}
