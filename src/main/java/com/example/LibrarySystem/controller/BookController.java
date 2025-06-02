package com.example.LibrarySystem.controller;

import com.example.LibrarySystem.dto.BookDTO;
import com.example.LibrarySystem.dto.BookRequest;
import com.example.LibrarySystem.dto.BookWithDetailsDTO;
import com.example.LibrarySystem.entity.Author;
import com.example.LibrarySystem.entity.Book;
import com.example.LibrarySystem.mapper.BookMapper;
import com.example.LibrarySystem.repository.AuthorRepository;
import com.example.LibrarySystem.repository.BookRepository;
import com.example.LibrarySystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

//    @GetMapping("/test")
//    public String test() {
//        return "Spring Boot is running!";
//    }

//    @GetMapping("/test/database")
//    public Map<String, Object> testDatabase() {
//        Map<String, Object> result = new HashMap<>();
//        result.put("message", "Database connection successful!");
//        result.put("book_count", bookRepository.count());
//        return result;
//    }

//    @GetMapping
//    public List<Book>getBooks(){
//        return bookService.getAllBooks();
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> findBookByTitle(@RequestParam String title){
        List<Book> books = bookService.getBookByTitle(title);
        if (!books.isEmpty()){
            return ResponseEntity.ok(books);
        }else {
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Book book = BookMapper.fromRequest(request, author);
        Book savedBook = bookRepository.save(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(BookMapper.toDTO(savedBook));
    }
//
//    @GetMapping
//    public ResponseEntity<List<BookWithDetailsDTO>>getAllBookWithDetails(){
//        List<Book> books = bookService.getAllBooks();
//        List<BookWithDetailsDTO> bookWithDetailsDTOList = books.stream()
//                .map(BookMapper::toDetailedDTO).toList();
//        return ResponseEntity.ok(bookWithDetailsDTOList);
//    }

    @GetMapping
    public ResponseEntity<Page<Book>> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Boolean available,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Book> result;
        if (title != null || available != null) {
            result = bookService.getFilteredBooks(title, available, pageable);
        } else {
            result = bookService.getAllBooks(pageable);
        }

        return ResponseEntity.ok(result);
    }


}
