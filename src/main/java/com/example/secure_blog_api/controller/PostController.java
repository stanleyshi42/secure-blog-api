package com.example.secure_blog_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.secure_blog_api.entity.Post;
import com.example.secure_blog_api.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Object> addPost(@RequestBody Post post) {
        Post result = postService.addPost(post);

        if (result.getId() > 0)
            return ResponseEntity.ok(result);
        return ResponseEntity.status(404).body("Error: failed to save post");
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/unapproved")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUnapprovedPosts() {
        return ResponseEntity.ok(postService.getUnapprovedPosts());
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllApprovedPosts() {
        return ResponseEntity.ok(postService.getApprovedPosts());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getPostsByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(postService.getPostsByUserId(userId));
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> approvePostById(@PathVariable long id) {
        int result = postService.approvePostById(id);

        if (result == 0)
            return ResponseEntity.status(404).body("Error: post not found");
        return ResponseEntity.ok(result);
    }

}
