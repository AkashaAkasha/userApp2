package com.example.userapp2.service;

import com.example.userapp2.exception.UserExistingEmailException;
import com.example.userapp2.exception.UserNotFoundException;
import com.example.userapp2.model.Users;
import com.example.userapp2.repository.UserRepository;
import com.example.userapp2.response.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    @Override
    public ResponseEntity<Object> save(Users user) {
        if (userRepository.findByEmail(user.getEmail()).get().getEmail().equals(user.getEmail())) throw new UserExistingEmailException("User with this email exist");
        long id = userRepository.save(user).getId();
        return ResponseHandler.responseBuilder("user was saved", HttpStatus.OK, id);
    }

    @Override
    public ResponseEntity<Object> findById(long id) {
        if (userRepository.findById(id).isEmpty()) throw new UserNotFoundException("User does not exist");
        return ResponseHandler.responseBuilder("serched user", HttpStatus.OK, userRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<Object> updatedUser(long id) {
        if (userRepository.findById(id).isEmpty()) throw new UserNotFoundException("User does not exist");
        Users user = userRepository.findById(id).get();
        Map<String, String> updateResponce = new HashMap<>();
        updateResponce.put("id", user.getId()+"");
        updateResponce.put("previous status", user.getStatus());
        if (user.getStatus().equalsIgnoreCase("offline")){
            user.setStatus("online");
        } else user.setStatus("offline");
        updateResponce.put("current status", user.getStatus());
        userRepository.save(user);
        return ResponseHandler.responseBuilder("uppdated user", HttpStatus.OK, updateResponce);
    }
}
