package com.jobsphere.post.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsphere.post.model.Post;
import com.jobsphere.post.repository.PostRepository;

@Service
public class ProfilePostService {

	/*
		For Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(ProfilePostService.class);

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostService postService;

	/*
		Create: to create new post
		@param
			String: title = title for the post;
			String: url = the optional url associated with the post (e.g., img/gif);
			String: content = content for post;
		@return
			Post: A ResponseEntity containing a ResponseObject with the result of the {creation operation or error response} accordingly;
	 */
	public Post create(UUID profile_id, String title, String url, String content) {
		// Create the post using a post model
		var post = new Post(profile_id, title, url, content);

		try {
			// Save Post to the database
			postRepository.save(post);

			// log the successful creation
			log.debug("Post:{} successfully created", post.getId());

			// return the retrieved post just created
			return postService.read(post.getId());
		} catch (Exception e) {
			// Handle the error and return null to prevent revealing potential sensitive information.
			log.error("While creating the post: {}, Error occurred at: {}", post.getId(), e.getMessage());
			return null;
		}
	}

	/*
		Read Service Method: To retrieve All Post with specific profile Id
		@param
			UUID: post_id = id of specific post to retrieve
		@return
			List<Post> = return post with the specific ID or Error response accordingly.
	 */

	public List<Post> readAllByProfileId(UUID profile_id) {

		try {
			// Retrieve all posts using repositories from the database.

			// return successful retrieval

			List<Post> all_posts = postRepository.findAllPostByProfileId(profile_id);
			log.debug(all_posts.toString());
			return all_posts;
		} catch (Exception e) {
			// Handle internal server error and return null to prevent revealing potential sensitive information.
			log.error("Error while accessing all posts by profile id");
			return null;
		}
	}


}
