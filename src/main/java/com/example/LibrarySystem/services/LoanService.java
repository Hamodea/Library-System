package com.example.LibrarySystem.services;


import com.example.LibrarySystem.entity.Book;
import com.example.LibrarySystem.entity.Loan;
import com.example.LibrarySystem.repository.BookRepository;
import com.example.LibrarySystem.repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private BookRepository bookRepository;


    @Transactional
    public Loan createLoan(Loan loan) {
        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        // Sätt datum
        loan.setBorrowedDate(LocalDateTime.now());
        loan.setDueDate(LocalDateTime.now().plusDays(14));  // t.ex. 2 veckor lån

        return loanRepository.save(loan);
    }

    public Optional<Loan> getLoanId(Long id){
        return loanRepository.findById(id);
    }


}
