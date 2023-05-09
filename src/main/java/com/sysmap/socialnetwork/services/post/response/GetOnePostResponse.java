package com.sysmap.socialnetwork.services.post.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.sysmap.socialnetwork.models.post.Comment;
import com.sysmap.socialnetwork.models.post.Like;
import com.sysmap.socialnetwork.models.post.Post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class GetOnePostResponse {

	private UUID postId;
	private LocalDateTime date;
	private String content;
	private UUID userId;
	private String userName;

	@Setter(AccessLevel.NONE)
	private Set<Like> likes = new HashSet<>();

	@Setter(AccessLevel.NONE)
	private List<Comment> comments = new ArrayList<>();
	
	
	public GetOnePostResponse(Post entity) {
		postId = entity.getId();
		userName = entity.getUserName();
		userId = entity.getUserId();
		content = entity.getContent();
		date = entity.getDate();
		likes = entity.getLikes();
		comments = entity.getComments();
	}
}
