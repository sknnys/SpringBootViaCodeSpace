package com.hszindev.springbootviacodespace.repository;


import com.hszindev.springbootviacodespace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    List<User> findAllByActiveTrue();
    List<User> findAllByActiveFalse();
}
