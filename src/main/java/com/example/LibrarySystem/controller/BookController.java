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
import org.springframework.data.domain.*;
import org.springframework.http.*;
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


    @GetMapping("/all")
    public List<Book>getAllBook(){
        return bookRepository.findAll();
    }
    /**
     * GET /books/details
     * Returnerar en fullständig lista över alla böcker med detaljerad författarinformation.
     */
    @GetMapping("/details")
    public ResponseEntity<List<BookWithDetailsDTO>> getAllBookWithDetails() {
        List<Book> books = bookService.getAllBooks();
        List<BookWithDetailsDTO> bookWithDetailsDTOList = books.stream()
                .map(BookMapper::toDetailedDTO)
                .toList();
        return ResponseEntity.ok(bookWithDetailsDTOList);
    }

    /**
     * GET /books/search
     * Söker efter böcker baserat på exakt titelmatchning.
     * Returnerar en lista med böcker som matchar titeln.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Book>> findBookByTitle(@RequestParam String title) {
        List<Book> books = bookService.getBookByTitle(title);
        if (!books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * POST /books
     * Skapar en ny bok baserat på inkommande BookRequest DTO.
     * Författaren hämtas via authorId.
     */
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Book book = BookMapper.fromRequest(request, author);
        Book savedBook = bookService.saveBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(BookMapper.toDTO(savedBook));
    }

    /**
     * GET /books
     * Returnerar en paginerad lista med böcker.
     * Stöd för valfria filter (titel, tillgänglighet) och sortering.
     */
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
            result = bookService.getAllBooksWithoutFilter(pageable);
        }

        return ResponseEntity.ok(result);
    }

    // Delete Books
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBookById(id);
            return ResponseEntity.ok("Book deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Error deleting book: " + e.getMessage());
        }
    }



}
