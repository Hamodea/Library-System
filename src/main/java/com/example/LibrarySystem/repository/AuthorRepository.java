package com.example.LibrarySystem.repository;


import com.example.LibrarySystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    public List<Author>findAuthorByLastName(String lastName);


    @Query(value = """
    SELECT CONCAT(a.first_name, ' ', a.last_name) AS authorFullName,
           b.title AS bookTitle
    FROM authors a
    JOIN books b ON a.author_id = b.author_id
    GROUP BY a.author_id, a.first_name, a.last_name, b.title
    HAVING COUNT(b.book_id) > 1
    """, nativeQuery = true)
    List<Object[]> findAuthorsWithMultipleBooksAndTitles();


}
