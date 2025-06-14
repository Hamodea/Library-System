package com.example.LibrarySystem.dto;

import java.time.LocalDateTime;

public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime registration;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDateTime registration) {
        this.registration = registration;
    }
}
