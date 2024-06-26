package com.example.secure_blog_api.controller;

import com.example.secure_blog_api.entity.User;
import com.example.secure_blog_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User result = userService.addUser(user);

        if (result.getId() > 0)
            return ResponseEntity.ok(result);
        return ResponseEntity.status(404).body("Error: failed to save user");
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getUserById(@PathVariable long id) {
        User result = userService.getUserById(id);
        if (result == null)
            return ResponseEntity.status(404).body("Error: user not found");
        return ResponseEntity.ok(result);

    }

    // Delete a user by ID; return deleted user if successful
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deleteUserById(@PathVariable long id) {
        User result = userService.getUserById(id);
        if (result == null)
            return ResponseEntity.status(404).body("Error: user not found");

        userService.deleteUserById(id);
        return ResponseEntity.ok(result);
    }

}
