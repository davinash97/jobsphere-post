package com.jobsphere.post.repository;

import com.jobsphere.post.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, java.util.UUID> {

	// Custom Query to get profile ID from post
	@Query("SELECT p.profile_id FROM Post p")
	UUID getProfileId(@Param("profile_id") UUID profile_id);

	@Query("SELECT COUNT(p) > 0 FROM Post p WHERE profile_id =: profile_id")
	boolean profileExistsById(@Param("profile_id") UUID profile_id);

	@Query("SELECT p FROM Post p WHERE p.profile_id = :profile_id")
	List<Post> findAllPostByProfileId(@Param("profile_id") UUID profile_id);

	@Query("SELECT p FROM Post p WHERE p.post_id = :post_id")
	Post findByPostId(@Param("post_id") UUID post_id);
}
