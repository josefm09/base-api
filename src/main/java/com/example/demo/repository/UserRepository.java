package com.example.demo.repository;

import com.example.demo.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String username);

    Boolean existsByFullName(String username);

    Boolean existsByEmail(String email);
}
