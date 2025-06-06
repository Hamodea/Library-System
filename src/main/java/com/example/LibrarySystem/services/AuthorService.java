package com.example.LibrarySystem.services;

import com.example.LibrarySystem.entity.Author;
import com.example.LibrarySystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }

    public List<Author>getAuthorByLastName(String lastName){
        return authorRepository.findAuthorByLastName(lastName);
    }

    public Author saveAuthor(Author author){
        return authorRepository.save(author);
    }


}
