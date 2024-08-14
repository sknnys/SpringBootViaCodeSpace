package com.hszindev.springbootviacodespace.controller;

import com.hszindev.springbootviacodespace.model.User;
import com.hszindev.springbootviacodespace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/removed")
    public ResponseEntity<List<User>> getRemovedUsers() {
        List<User> removedUsers = userService.getRemovedUsers();
        return ResponseEntity.ok(removedUsers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }
}
