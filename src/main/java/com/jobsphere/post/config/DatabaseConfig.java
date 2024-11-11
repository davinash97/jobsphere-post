package com.jobsphere.post.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	@Value("${spring.database.url")
	private String DB_URL;

	@Value("${spring.datasource.username")
	private String DB_NAME;

	@Value("${spring.datasrouce.password")
	private String DB_PASS;
}
