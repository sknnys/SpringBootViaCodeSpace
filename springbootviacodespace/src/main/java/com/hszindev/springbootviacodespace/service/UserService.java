package com.hszindev.springbootviacodespace.service;

import com.hszindev.springbootviacodespace.model.User;
import com.hszindev.springbootviacodespace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> saveUser(User user) {
        Optional<User> existingUser = userRepository.findByName(user.getName());
        
        if (existingUser.isPresent()) {
            if (existingUser.get().getActive()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("Usuário já registrado no banco de dados.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("Usuário já foi cadastrado e removido do banco de dados.");
            }
        }
        
        try {
            User savedUser = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao salvar o usuário: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAllByActiveTrue(); // Retorna apenas usuários ativos
    }

    public List<User> getRemovedUsers() {
        return userRepository.findAllByActiveFalse(); // Retorna apenas usuários removidos
    }

    public ResponseEntity<?> deleteUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setActive(false);
            userRepository.save(existingUser);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // No content for successful delete
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Usuário não encontrado.");
        }
    }
}
