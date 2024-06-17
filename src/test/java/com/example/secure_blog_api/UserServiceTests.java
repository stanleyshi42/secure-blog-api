package com.example.secure_blog_api;

import com.example.secure_blog_api.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTests {

	@Autowired
	UserService service;

	@Test
	void contextLoads() {
		assertThat(service).isNotNull();
	}



}
