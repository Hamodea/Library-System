package com.example.LibrarySystem.repository;

import com.example.LibrarySystem.entity.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository <Book, Long> {

    public List<Book> findByTitle(String title);


    @Query("""
            SELECT b FROM Book b
            WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))
              AND (:available IS NULL OR b.availableCopies > 0)
            """)
    Page<Book> findFilteredBooks(@Param("title") String title,
                                 @Param("available") Boolean available,
                                 Pageable pageable);


}


