package com.example.secure_blog_api.service;

import com.example.secure_blog_api.entity.User;
import com.example.secure_blog_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(long id) {
        Optional<User> result = userRepo.findById(id);
        return userRepo.findById(id).orElse(null);
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

}
