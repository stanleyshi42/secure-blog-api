package com.example.secure_blog_api.repository;

import com.example.secure_blog_api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.approved = TRUE")
    List<Post> findAllApprovedPosts();

    @Query("SELECT p FROM Post p WHERE p.approved = FALSE")
    List<Post> findAllUnapprovedPosts();

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.approved = TRUE WHERE p.id = ?1")
    int approvePostById(long id);

    List<Post> findAllPostsByUserId(long id);
}
