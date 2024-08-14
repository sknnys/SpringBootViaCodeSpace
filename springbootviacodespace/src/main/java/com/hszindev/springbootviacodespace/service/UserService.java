package com.hszindev.springbootviacodespace.service;

import com.hszindev.springbootviacodespace.model.User;
import com.hszindev.springbootviacodespace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        Optional<User> existingUser = userRepository.findByName(user.getName());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Usuario com esse nome já foi cadastrado.");
        }
            return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            User userToUpdate = user.get();
            userToUpdate.setActive(false);
            userRepository.save(userToUpdate);
        }
    }
}
