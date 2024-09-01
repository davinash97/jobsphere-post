package com.jobsphere.post.service;

import com.jobsphere.post.model.Post;
import com.jobsphere.post.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonPostService {

	private static final Logger log = LoggerFactory.getLogger(CommonPostService.class);

	@Autowired
	private PostRepository postRepository;
	/*
		Read Service Method: To retrieve All Post
		@return
			List<Post> = return all posts.
	 */

	public List<Post> readAll() {
		return postRepository.findAll();
	}
}
