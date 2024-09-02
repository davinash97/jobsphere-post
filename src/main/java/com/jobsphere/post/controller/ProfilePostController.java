package com.jobsphere.post.controller;

import com.jobsphere.post.model.Post;
import com.jobsphere.post.model.ResponseObject;
import com.jobsphere.post.service.PostService;
import com.jobsphere.post.service.ProfilePostService;
import com.jobsphere.post.utils.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/*
 * Controller for managing Post-related operations.
 * Provides endpoints for creating & reading posts.
 */

@RestController
@RequestMapping("/{profile_id}/post")
public class ProfilePostController {

	/*
		For Logger
	 */

	private static final Logger log = LoggerFactory.getLogger(ProfilePostController.class);

	@Autowired
	private PostService postService;

	@Autowired
	private ProfilePostService profilePostService;

	/*
		Create Controller Method: To create new Post
		@param
			String: title = title for the post;
			String: url = The optional url associated with the post (e.g., images/gif);
			String: content = content for post;
		@return
			ResponseObject: A ResponseEntity containing a ResponseObject with the result of the {creation operation or error response} accordingly;
	 */

	@PostMapping
	public ResponseObject<?> create(@PathVariable String profile_id,
									@RequestParam String title,
									@RequestParam(required = false) String url,
									@RequestParam String content) {
		try {
			// Set url to null if it is empty
			if(url!=null && url.isEmpty()) url = null;

			//	Create the post using the service
			var post = profilePostService.create(UUID.fromString(profile_id), title, url, content);

			// Handle the case if post-service returns null
			if(post == null) {
				log.debug("Post is null");
				return Error.NO_CONTENT.getResponse();
			}

			// Log the success
			log.debug("Post created with id {}", post.getId());

			// return success after post-creation with data
			return new ResponseObject<>("success", HttpStatus.OK.value(),postService.read(post.getId()));
		} catch (Exception e) {
			// Handle exceptions and return an error response
			return handleException(e);
		}

	}

	/*
		Read Controller Method from specific profile ID: To retrieve Post from specific profile Id
		@param
			UUID: profile_id = id of specific profile to retrieve all posts
		@return
			ResponseObject: ResponseEntity = return post with the specific ID or Error response accordingly.
	 */

	@GetMapping
	public ResponseEntity<ResponseObject<?>> readAllByProfileId(@PathVariable UUID profile_id) {
		try {
			// retrieve all posts with specific profile ID
			var posts = profilePostService.readAllByProfileId(profile_id);

			// handle if post is null
			if (posts == null || posts.isEmpty()) {
				// log the null
				log.debug("Post is null, either profile id invalid or internal sever occurred");

				// return the error response
				return ResponseEntity.ofNullable(Error.NOT_FOUND.getResponse());
			}

			log.debug("Successfully retrieved posts by {}", profile_id);

			// return the response
			return ResponseEntity.ok(new ResponseObject<List<Post>>("success", HttpStatus.OK.value(), posts));
		} catch (Exception e) {
			log.error("while fetching all posts from one profile, error occurred at {}.", e.getMessage());
			return ResponseEntity.internalServerError().body(handleException(e));
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
