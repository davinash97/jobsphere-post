package com.jobsphere.post.controller;

import com.jobsphere.post.model.ResponseObject;
import com.jobsphere.post.service.PostService;
import com.jobsphere.post.utils.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Controller for managing Post-related operations.
 * Provides endpoints for reading, updating, and deleting posts.
 */

@RestController
@RequestMapping("/{profile_id}/post/{post_id}")
public class PostController {

	/*
		For Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostService postService;

	/*
		Read Controller Method: To retrieve Post with specific Id
		@param
			UUID: post_id = id of specific post to retrieve
		@return
			ResponseObject: ResponseEntity = return post with the specific ID or Error response accordingly.
	 */

	@GetMapping
	public ResponseEntity<ResponseObject<?>> read(@PathVariable UUID post_id) {

		// read the post using post-service
		var post = postService.read(post_id);

		try {

			// Handle case if post is null and return the error response.
			if (post == null) {
				log.debug("Post is null");
				return ResponseEntity.ok(Error.NO_CONTENT.getResponse());
			}

			// return the post after successful retrieval
			return ResponseEntity.ok(new ResponseObject<>("success", HttpStatus.OK.value(), post));
		} catch (Exception e) {
			// Handle internal server error issues and return the response without revealing the sensitive information.
			return ResponseEntity.internalServerError().body(handleException(e));
		}
	}

	/*
		Update Controller Method: To update post with specific Id
		@param
			UUID: post_id = id of the specific post
			String: title = title of the specific post
			String: url = url of the specific post
			String: content = content of the specific post
		@return
			ResponseObject = returns the updated post with specific ID.
	 */

	@PutMapping
	public ResponseObject<?> update(@PathVariable UUID post_id,
									@PathVariable UUID profile_id,
									 @RequestParam(required = false) String title,
									 @RequestParam(required = false) String url,
									 @RequestParam(required = false) String content) {

		// update the post using the post-service
		var post = postService.update(profile_id, post_id, title, url, content);

		try {

			// Handle the case if post is null
			if (post == null) {
				log.debug("Post is null");
				return Error.NO_CONTENT.getResponse();
			}

			// log the success
			log.debug("Successfully updated {}", post_id);

			// return the post after successful update
			return new ResponseObject<>("success", HttpStatus.OK.value(), post);
		} catch (Exception e) {
			// Handle update issue and return the appropriate response
			return handleException(e);
		}
	}

	/*
		Delete: To delete post with specific Id
		@param
			UUID: post_id = id of the specific post
		@return
			ResponseObject = returns the response for deletion of post with specific ID.
	*/

	@DeleteMapping
	public ResponseObject<?> delete(@PathVariable UUID profile_id, @PathVariable UUID post_id) {

		try {
			// Handle the successful deletion
			if(postService.delete(profile_id, post_id)) {
				// Log the successful deletion
				log.debug("Successfully deleted {}", post_id);
				// return the successful deletion
				return new ResponseObject<>("success", HttpStatus.OK.value(), true);
			}

			// Handle the deletion error
			log.debug("{} not found", post_id);

			// return post not found
			return Error.NOT_FOUND.getResponse();
		} catch (Exception e) {
			// Handle the exception and return the appropriate response
			return handleException(e);
		}
	}

	/*
		For Exception
		Internal Error method
	 */

	public ResponseObject<?> handleException (Exception e) {
		log.error("error occurred: {}", e.getMessage(), e);
		return Error.INTERNAL_ERROR.getResponse();
	}
}