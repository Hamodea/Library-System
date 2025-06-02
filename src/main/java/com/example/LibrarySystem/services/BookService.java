package com.example.LibrarySystem.services;

import com.example.LibrarySystem.entity.Book;
import com.example.LibrarySystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book>getBookByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public Book saveBook(Book book) {
        if (book.getId() != null) {
            Optional<Book> existing = bookRepository.findById(book.getId());
            if (existing.isPresent()) {
                Book b = existing.get();
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
                return bookRepository.save(b);
            }
        }
        return bookRepository.save(book); // ny bok
    }


    // 🧭 Enkel pagination (utan filter)
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    // 🔍 Filtrering + pagination
    public Page<Book> getFilteredBooks(String title, Boolean available, Pageable pageable) {
        return bookRepository.findFilteredBooks(title, available, pageable);
    }
}
