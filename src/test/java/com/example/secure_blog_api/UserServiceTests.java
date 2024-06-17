package com.example.secure_blog_api;

import com.example.secure_blog_api.controller.UserController;
import com.example.secure_blog_api.entity.User;
import com.example.secure_blog_api.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTests {

    @Autowired
    UserService service;

    @Test
    void contextLoads() {
        assertThat(service).isNotNull();
    }

    @Test
    void testCRUD() {
        String expectedUsername = "test";
        String expectedPassword = "pass";
        String expectedRoles = "ADMIN,USER";

        // Test create
        User expectedUser = new User(0, expectedUsername, expectedPassword, expectedRoles);
        User actualUser = service.addUser(expectedUser);

        assertEquals(expectedUsername, actualUser.getUsername());

        // Test read
        actualUser = service.getUserById(actualUser.getId());
        assertEquals(expectedUsername, actualUser.getUsername());

    }

}
