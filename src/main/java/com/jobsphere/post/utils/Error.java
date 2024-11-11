package com.jobsphere.post.utils;

import com.jobsphere.post.model.ResponseObject;
import org.springframework.http.HttpStatus;

public enum Error {
	INTERNAL_ERROR("error", HttpStatus.INTERNAL_SERVER_ERROR),
	NO_CONTENT("bad", HttpStatus.NO_CONTENT),
	NOT_FOUND("invalid id", HttpStatus.NOT_FOUND);

	private final String message;
	private final HttpStatus status;

	Error(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public ResponseObject<?> getResponse() {
		return new ResponseObject<>(message, status.value(), "invalid id");
	}
}
