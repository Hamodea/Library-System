package com.example.LibrarySystem.controller;

import com.example.LibrarySystem.dto.AuthorBookDTO;
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
    public ResponseEntity<List<Author>> getAll() {
        List<Author> authors = authorService.getAllAuthor();
        return ResponseEntity.ok(authors); // 200 OK, även om listan är tom
    }


    @GetMapping("/name")
    public ResponseEntity<List<Author>> findAuthorByName(@RequestParam String lastName) {
        List<Author> authors = authorService.getAuthorByLastName(lastName);
        return ResponseEntity.ok(authors);
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

    @GetMapping("/multi-book")
    public ResponseEntity<List<AuthorBookDTO>> getAuthorsWithMultipleBooksAndTitles() {
        List<Object[]> rows = authorService.findAuthorsWithMultipleBooksAndTitles();

        List<AuthorBookDTO> dtos = rows.stream()
                .map(row -> new AuthorBookDTO(
                        (String) row[0],
                        (String) row[1]
                ))
                .toList();

        return ResponseEntity.ok(dtos);
    }


}
