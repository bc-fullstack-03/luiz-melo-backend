package com.sysmap.socialnetwork.config;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.sysmap.socialnetwork.model.Post;
import com.sysmap.socialnetwork.model.User;
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
		
		var luiz = new User("Luiz", "luiz@gmail.com", "12345");
		var beatriz = new User("Bea", "bea@email.com", "12345");
		
		userRepository.saveAll(Arrays.asList(luiz, beatriz));
		
		var post1 = new Post(LocalDateTime.now(), "Bom dia, amigos", luiz.getId());
		var post2 = new Post(LocalDateTime.now(), "isso é muito bom, cara", luiz.getId());
		var post3 = new Post(LocalDateTime.now(), "Vamos assistir algum filme ruim?", beatriz.getId());
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3));
		
		
	}
	
}
