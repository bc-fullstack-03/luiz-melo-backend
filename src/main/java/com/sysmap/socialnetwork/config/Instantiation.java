package com.sysmap.socialnetwork.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.sysmap.socialnetwork.model.User;
import com.sysmap.socialnetwork.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		var luiz = new User("Luiz", "luiz@gmail.com", "12345");
		var beatriz = new User("Bea", "bea@email.com", "12345");
		
		userRepository.saveAll(Arrays.asList(luiz, beatriz));
		
	}
	
}
