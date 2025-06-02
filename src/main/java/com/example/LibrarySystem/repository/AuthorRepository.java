package com.example.LibrarySystem.repository;


import com.example.LibrarySystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    public List<Author>findAuthorByLastName(String lastName);
}
