//package com.sysmap.socialnetwork.config;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.sysmap.socialnetwork.models.post.Author;
//import com.sysmap.socialnetwork.models.post.Comment;
//import com.sysmap.socialnetwork.models.post.Like;
//import com.sysmap.socialnetwork.models.post.Post;
//import com.sysmap.socialnetwork.models.user.Follower;
//import com.sysmap.socialnetwork.models.user.User;
//import com.sysmap.socialnetwork.repositories.PostRepository;
//import com.sysmap.socialnetwork.repositories.UserRepository;
//
//@Configuration
//public class Instantiation implements CommandLineRunner {
//
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	private PostRepository postRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		postRepository.deleteAll();
//		userRepository.deleteAll();
//
//		var luiz = new User("Luiz", "luiz@gmail.com");
//		luiz.setPassword(passwordEncoder.encode("123"));
//
//		var beatriz = new User("Bea", "bea@email.com");
//		beatriz.setPassword(passwordEncoder.encode("123"));
//
//		var julio = new User("Julio", "julio@email.com");
//		julio.setPassword(passwordEncoder.encode("123"));
//
//		var oliver = new User("Oliver", "oliver@email.com");
//		oliver.setPassword(passwordEncoder.encode("123"));
//
//		userRepository.saveAll(Arrays.asList(luiz, beatriz, oliver, julio));
//
//		var f2 = new Follower(luiz);
//
//		luiz.getFollowers().add(new Follower(beatriz));
//		oliver.getFollowers().addAll(Arrays.asList(new Follower(beatriz), f2));
//
//		userRepository.saveAll(Arrays.asList(luiz, oliver));
//
//		var post1 = new Post("asuasgasugsauasgua", luiz.getId(), luiz.getName());
//		var post2 = new Post("uuueiiqsgasugsauasgua", luiz.getId(), luiz.getName());
//		var post3 = new Post("iuuuuuuuuuuuuuuuuuashsasasauq", beatriz.getId(), beatriz.getName());
//		var post4 = new Post("iuuuuuuuuuuuuuuuuuashsasasauq", beatriz.getId(), beatriz.getName());
//
//		postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));
//
//		var like1 = new Like(new Author(luiz));
//		var like2 = new Like(new Author(julio));
//		var like3 = new Like(new Author(beatriz));
//		var like4 = new Like(new Author(oliver));
//
//		post3.getLikes().addAll(Arrays.asList(like1, like3, like4));
//		post2.getLikes().addAll(Arrays.asList(like2, like3, like4));
//
//		var c1 = new Comment("shsausauasasiassahu", new Author(luiz));
//		var c2 = new Comment("shsausausahu", new Author(oliver));
//		var c3 = new Comment("kkjjiiuushsausausahu", new Author(beatriz));
//
//		post1.getComments().add(c2);
//		post2.getComments().addAll(Arrays.asList(c1, c2, c3));
//
//		c1.getLikes().addAll(Arrays.asList(like1, like2, like3));
//		c1.getLikes().addAll(Arrays.asList(like1, like3));
//		c1.getLikes().addAll(Arrays.asList(like2, like3));
//
//		postRepository.saveAll(Arrays.asList(post1, post2, post3));
//
//	}
//
//}
