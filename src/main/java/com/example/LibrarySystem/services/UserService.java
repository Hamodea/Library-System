package com.example.LibrarySystem.services;

import com.example.LibrarySystem.dto.UserDTO;
import com.example.LibrarySystem.entity.User;
import com.example.LibrarySystem.mapper.UserMapper;
import com.example.LibrarySystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User>getAllUser(){
        return userRepository.findAll();
    }

    public Optional<UserDTO> findUserEmail(String email){
        return userRepository.findUserByEmail(email)
                .map(UserMapper::toDTO);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }



}
