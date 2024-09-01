package com.jobsphere.post.model;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class ResponseObject<T> {

	public String status;

	public Integer code;

	public T result;

	public ResponseObject (){};

	public ResponseObject (String status, T result) {
		this.status = status;
		this.result = result;
	}

	public ResponseObject (String status, Integer code, T result) {
		this.status = status;
		this.code = code;
		this.result = result;
	}
}
