package com.example.LibrarySystem.dto;

import java.time.LocalDateTime;

public class UserDTO {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final LocalDateTime registrationDate;

    public UserDTO(Long id, String firstName, String lastName, String email, LocalDateTime registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    // Getters
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public LocalDateTime getRegistrationDate() { return registrationDate; }
}
