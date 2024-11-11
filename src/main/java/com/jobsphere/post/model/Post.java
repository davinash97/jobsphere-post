package com.jobsphere.post.model;

import jakarta.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "post")
public class Post {

	private static final Logger log = LoggerFactory.getLogger(Post.class);

	@Column(nullable = false)
	private UUID profile_id;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID post_id;

	private String title;

	private String url;

	private String content;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime created_at;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updated_at;

	public Post(){}

	public Post(UUID profile_id, String title, String url, String content) {
		this.profile_id = profile_id;
		this.title = title;
		this.url = url;
		this.content = content;
		this.created_at = LocalDateTime.now();
		this.updated_at = LocalDateTime.now();
		log.debug("New Post created with Id {}", getId());
	}

	public UUID getProfile_id() {
		return profile_id;
	}

	public UUID getId() {
		return post_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "Post created with I'd: " + post_id + "Title: " + title;
	}
}
