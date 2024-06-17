package com.example.secure_blog_api;

import com.example.secure_blog_api.controller.UserController;
import com.example.secure_blog_api.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTests {

    @Autowired
    UserController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void securityAuthTests() {
        User user = new User(0, "test", "test", "USER");
        ResponseEntity<Object> result = controller.addUser(user);

        assertEquals(200, result.getStatusCode().value());

        result = controller.getAllUsers();
        assertEquals(200, result.getStatusCode().value());
    }

}
