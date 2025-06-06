package com.example.LibrarySystem.services;


import com.example.LibrarySystem.entity.Book;
import com.example.LibrarySystem.entity.Loan;
import com.example.LibrarySystem.repository.BookRepository;
import com.example.LibrarySystem.repository.LoanRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    private final BookRepository bookRepository;


    public LoanService(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }



    @Transactional
    public Loan createLoan(Loan loan) {
        Book book = loan.getBook();

        // 4. Kontrollera tillgängliga
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is not available for loan.");
        }


        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        loan.setBorrowedDate(LocalDateTime.now());
        loan.setDueDate(loan.getBorrowedDate().plusWeeks(2));

        return loanRepository.save(loan);
    }

    public Optional<Loan> getLoanId(Long id){
        return loanRepository.findById(id);
    }


}
