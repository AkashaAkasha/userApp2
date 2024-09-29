package com.example.userapp2.service;

import com.example.userapp2.model.Users;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    ResponseEntity<Object> save(Users user);
    ResponseEntity<Object> findById(long id);
    ResponseEntity<Object> updatedUser(long i);
}
