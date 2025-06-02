package com.example.LibrarySystem.mapper;

import com.example.LibrarySystem.dto.UserDTO;
import com.example.LibrarySystem.dto.UserRequest;
import com.example.LibrarySystem.entity.User;

public class UserMapper {

    // From request (API input) → entity
    public static User fromRequest(UserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    // From entity → response DTO (API output)
    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRegistration()
        );
    }
}
