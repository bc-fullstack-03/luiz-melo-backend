package com.sysmap.socialnetwork.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.sysmap.socialnetwork.model.Follower;
import com.sysmap.socialnetwork.model.user.User;
import com.sysmap.socialnetwork.repositories.PostRepository;
import com.sysmap.socialnetwork.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		var luiz = new User("Luiz", "luiz@gmail.com", "12343645");
		User beatriz = new User("Bea", "bea@email.com", "12345");
		var julio = new User("Julio", "julio@email.com", "1332345");
		var oliver = new User("Oliver", "oliver@email.com", "1234545");
		
				
		userRepository.saveAll(Arrays.asList(luiz, beatriz, oliver, julio));
		
		var f2 = new Follower(luiz);
		
		luiz.getFollowers().add(new Follower(beatriz));
		oliver.getFollowers().addAll(Arrays.asList(new Follower(beatriz),f2));
		
		userRepository.saveAll(Arrays.asList(luiz, oliver));
		
	}
	
}
