package com.jobsphere.post.controller;

import com.jobsphere.post.model.ResponseObject;
import com.jobsphere.post.service.CommonPostService;
import com.jobsphere.post.utils.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class CommonPostController {

	/*
		For Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(CommonPostController.class);


	@Autowired
	private CommonPostService commonPostService;

	/*
		Read Controller Method for All post: To retrieve all Posts
		@return
			ResponseObject: ResponseEntity = return all posts.
	 */

	@GetMapping
	public ResponseEntity<ResponseObject<?>> readAll() {
		return ResponseEntity.ok(new ResponseObject<>("success", HttpStatus.OK.value(), commonPostService.readAll()));
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
