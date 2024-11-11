package com.jobsphere.post.service;

import com.jobsphere.post.model.Post;

import com.jobsphere.post.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.UUID;

/**
 * Service for managing Post-related operations.
 * Provides Service for creating, reading, updating, and deleting posts.
 */

@Service
public class PostService {

	/*
		For Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(PostService.class);

	@Autowired
	private PostRepository postRepository;

	/*
		Read Service Method: To retrieve Post with specific Id
		@param
			UUID: post_id = id of specific post to retrieve
		@return
			Post = return post with the specific ID or Error response accordingly.
	 */

	public Post read(UUID post_id) {

		// Handle if post_id doesn't exists
		if(!postIdExists(post_id))
			return null;

		try {
			// read the post using repository from database
			var post = postRepository.findByPostId(post_id);

			if(post == null)
				return null;

			// log the retrieved post
			log.debug("Accessed post: {}", post_id);

			// return the successful post
			return post;
		} catch (Exception e) {
			// Handle internal server error and return the error response without revealing potential sensitive information.
			log.error("While accessing {} error occurred at {}", post_id, e.getMessage());
			return null;
		}
	}

	/*
		Update Service Method: To update post with specific Id
		@param
			UUID: post_id = id of the specific post
			String: title = title of the specific post
			String: url = url of the specific post
			String: content = content of the specific post
		@return
			Post = returns the updated post with specific ID.
	 */
	public Post update(UUID profile_id, UUID post_id, String title, String url, String content) {

		// Handle if post_id is null
		if(post_id.toString() == null)
			return null;
		// handle if post_id exists or else return null
		else if(!postIdExists(post_id))
			return null;

		try {

			// retrieve existing data in post
			var existingPost = read(post_id);

			// Handle if profile ID matches
			if(!profile_id.equals(existingPost.getProfile_id()))
				return null;

			// Handle if title is not null
			if(title != null)
				existingPost.setTitle(title);

			// Handle if url is not null
			if(url != null)
				existingPost.setUrl(url);

			// Handle if content is not null
			if(content != null)
				existingPost.setContent(content);

			// Updated the time in database
			existingPost.setUpdated_at(LocalDateTime.now());

			// Save the updated post
			postRepository.save(existingPost);

			// log the successful update
			log.debug("Post: {} updated successfully", post_id);

			// return the newly update post
			return read(post_id);
		} catch (Exception e) {
			// Handle the internal server error and return null to prevent revealing potential sensitive information.
			log.error("While updating the post: {} Error occurred: {}", post_id, e.getMessage());
			return null;
		}
	}

	/*
		Delete: To delete post with specific Id
		@param
			UUID: post_id = id of the specific post
		@return
			Boolean = returns the response for deletion of post with specific ID.
	*/

	public Boolean delete(UUID profile_id, UUID post_id) {

		// Handle if post_id is null
		if(post_id.toString() == null || profile_id.toString() == null)
			return false;

		try {

			// check if the id exists in database.
			if(postIdExists(post_id)) {

				var post = read(post_id);

				// if id exists then delete it
				if(!profile_id.equals(post.getProfile_id()))
					return false;

				postRepository.deleteById(post_id);

				// log the successful deletion
				log.debug("Successfully deleted post: {}", post_id);

				// return true for successfull deletion
				return true;
			}
			// return false if id exists
			return false;
		} catch (Exception e) {
			// Handle the exception and return the appropriate response without revealing potential senstive information.
			log.error("While deleting the post: {} error occurred at {}", post_id, e.getMessage());
			return false;
		}
	}

	/*
		Service method to check if post_id exists
	 */

	public boolean postIdExists(UUID post_id) {
		return postRepository.existsById(post_id);
	}
}
