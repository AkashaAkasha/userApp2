package com.example.userapp2.controller;

import com.example.userapp2.model.Users;
import com.example.userapp2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class Controller {
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id){
        return userService.findById(Integer.parseInt(id));
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody Users user){
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUserStatus(@PathVariable String id) {
        return userService.updatedUser(Integer.parseInt(id));
    }
}
