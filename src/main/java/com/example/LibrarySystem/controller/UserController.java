package com.example.LibrarySystem.controller;

import com.example.LibrarySystem.dto.UserDTO;
import com.example.LibrarySystem.dto.UserRequest;
import com.example.LibrarySystem.entity.User;
import com.example.LibrarySystem.mapper.UserMapper;
import com.example.LibrarySystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


//Get User By Email
    @GetMapping("/email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        return userService.findUserEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<UserDTO>createUser(@RequestBody UserRequest userRequest){
        User user = UserMapper.fromRequest(userRequest);
        User saveUser = userService.saveUser(user);

        UserDTO responseDto = UserMapper.toDTO(saveUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }
    @GetMapping
    public ResponseEntity<List<UserDTO>>getUser(){
        List<User> users = userService.getAllUser();

        List<UserDTO> userDTOS = users.stream()
                .map(UserMapper::toDTO).toList();

        return ResponseEntity.ok(userDTOS);

    }

}
