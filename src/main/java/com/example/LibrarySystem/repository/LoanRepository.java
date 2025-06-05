package com.example.LibrarySystem.repository;

import com.example.LibrarySystem.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT l FROM Loan l WHERE l.user.id = :userId AND l.book.id = :bookId AND l.returnedDate IS NULL")
    Optional<Loan> findActiveLoanByUserAndBook(@Param("userId") Long userId, @Param("bookId") Long bookId);


    List<Loan> findByUserId(Long userId);

    List<Loan>findByBookId(Long bookId);



}
