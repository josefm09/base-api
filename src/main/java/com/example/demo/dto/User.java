package com.example.demo.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    private String _id;
    private String fullName;
    private String email;
    private String hash_password;
    private String token;
    private String created;
}
