package com.example.LibrarySystem.controller;

import com.example.LibrarySystem.dto.AuthorRequest;
import com.example.LibrarySystem.entity.Author;
import com.example.LibrarySystem.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author>getAll(){
        return authorService.getAllAuthor();
    }

    @GetMapping("/name")
    public List<Author>findAuthorByName(@RequestParam String lastName){
        return authorService.getAuthorByLastName(lastName);
    }

    @PostMapping
    public ResponseEntity<Author>createAuthor(@RequestBody AuthorRequest authorRequest){
        Author author = new Author();
        author.setFirstName(authorRequest.getFirstName());
        author.setLastName(authorRequest.getLastName());
        author.setBirthYear(authorRequest.getBirthYear());
        author.setNationality(authorRequest.getNationality());

        Author saved = authorService.saveAuthor(author);
        return ResponseEntity.status(201).body(saved);

    }
}
