package com.example.LibrarySystem.mapper;

import com.example.LibrarySystem.dto.BookDTO;
import com.example.LibrarySystem.dto.BookRequest;
import com.example.LibrarySystem.dto.BookWithDetailsDTO;
import com.example.LibrarySystem.entity.Author;
import com.example.LibrarySystem.entity.Book;

public class BookMapper {

    public static Book fromRequest(BookRequest request, Author author){
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setPublicationYear(request.getPublicationYear());
        book.setAvailableCopies(request.getAvailableCopies());
        book.setTotalCopies(request.getTotalCopies());
        book.setAuthor(author);
        return book;
    }
    // Enkel DTO utan författardetaljer
    public static BookDTO toDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getPublicationYear(),
                book.getAvailableCopies(),
                book.getTotalCopies()
        );
    }

    // Mer detaljerad DTO (inkl. författare)
    public static BookWithDetailsDTO toDetailedDTO(Book book) {
        return new BookWithDetailsDTO(
                book.getId(),
                book.getTitle(),
                book.getPublicationYear(),
                book.getAvailableCopies(),
                book.getTotalCopies(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName()
        );
    }
}
