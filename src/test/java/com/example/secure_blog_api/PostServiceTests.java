package com.example.secure_blog_api;

import com.example.secure_blog_api.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.secure_blog_api.entity.Post;

@SpringBootTest
@ActiveProfiles("test")
public class PostServiceTests {

    @Autowired
    PostService service;

    @Test
    void contextLoads() {
        assertThat(service).isNotNull();
    }

    @Test
    void testCRUD() {
        long expectedUserId = 4;
        String expectedText = "test";
        boolean expectedApproved = false;

        // Test create
        Post expectedPost = new Post(0, expectedUserId, expectedText, expectedApproved);
        Post actualPost = service.addPost(expectedPost);

        assertEquals(expectedUserId, actualPost.getUserId());
        assertEquals(expectedText, actualPost.getText());
        assertFalse(actualPost.isApproved());

        // Test read
        actualPost = service.getPostById(actualPost.getId());
        assertEquals(expectedText, actualPost.getText());

        // Test approving a post
        service.approvePostById(actualPost.getId());
        actualPost = service.getPostById(actualPost.getId());
        assertTrue(actualPost.isApproved());

    }
}
