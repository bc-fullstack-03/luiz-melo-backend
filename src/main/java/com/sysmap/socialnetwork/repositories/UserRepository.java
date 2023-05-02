package com.sysmap.socialnetwork.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sysmap.socialnetwork.model.User;

public interface UserRepository extends MongoRepository<User, UUID>{

}
