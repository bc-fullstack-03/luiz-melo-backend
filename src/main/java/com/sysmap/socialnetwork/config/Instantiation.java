package com.sysmap.socialnetwork.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.sysmap.socialnetwork.model.Comment;
import com.sysmap.socialnetwork.model.Like;
import com.sysmap.socialnetwork.model.Post;
import com.sysmap.socialnetwork.model.User;
import com.sysmap.socialnetwork.model.dto.AuthorDTO;
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
		var julio = new User("Julio", "julio@email.com", "12345");
		var oliver = new User("Oliver", "oliver@email.com", "12345");
		
		userRepository.saveAll(Arrays.asList(luiz, beatriz));
		
		var post1 = new Post( "Bom dia, amigos", new AuthorDTO(luiz));
		var post2 = new Post("isso é muito bom, cara", new AuthorDTO(luiz));
		var post3 = new Post("Vamos assistir algum filme ruim?", new AuthorDTO(beatriz));
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3));
		
		var comment1 = new Comment("Bom dia, meu amigo", new AuthorDTO(beatriz));		
		var comment2 = new Comment("com certeza é", new AuthorDTO(beatriz));		
	
		post1.getComments().add(comment1);
		post2.getComments().add(comment2);
		
		var like1 = new Like(new AuthorDTO(julio));
		var like2 = new Like(new AuthorDTO(oliver));
		var like3 = new Like(new AuthorDTO(luiz));
		var like4 = new Like(new AuthorDTO(beatriz));

		post1.getLikes().addAll(Arrays.asList(like1, like2, like3, like4));
		post2.getLikes().addAll(Arrays.asList(like1, like3, like3, like3));
		
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		comment1.getLikes().addAll(Arrays.asList(like1, like2));
		comment2.getLikes().addAll(Arrays.asList(like4));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}
	
}
