package com.example.secure_blog_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.secure_blog_api.entity.Post;
import com.example.secure_blog_api.repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepo;

    public Post addPost(Post post) {
        return postRepo.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public List<Post> getUnapprovedPosts(){
        return postRepo.findAllUnapprovedPosts();
    }

    public List<Post> getApprovedPosts(){
        return postRepo.findAllApprovedPosts();
    }

    public Post getPostById(long id) {
        return postRepo.findById(id).orElse(null);
    }

    public List<Post> getPostsByUserId(long id){
        return postRepo.findAllPostsByUserId(id);
    }

    public int approvePostById(long id){
        return postRepo.approvePostById(id);
    }

}
