package com.example.LibrarySystem.services;

import com.example.LibrarySystem.entity.Book;
import com.example.LibrarySystem.entity.Loan;
import com.example.LibrarySystem.entity.User;
import com.example.LibrarySystem.repository.BookRepository;
import com.example.LibrarySystem.repository.LoanRepository;

import com.example.LibrarySystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoanService loanService;

    // TEST 1: Kontrollera att borrowedDate och dueDate sätts korrekt
    @Test
    void testDueDateIsSetCorrectly() {
        // Arrange
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Fake Book");
        book.setAvailableCopies(5);

        User user = new User();
        user.setId(1L);
        user.setFirstName("Test");
        user.setLastName("User");

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);

        // Vi mockar save eftersom det anropas EFTER kontrollen
        when(loanRepository.save(any(Loan.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        Loan result = loanService.createLoan(loan);

        // Assert
        assertNotNull(result.getBorrowedDate(), "Borrowed date should not be null");
        assertNotNull(result.getDueDate(), "Due date should not be null");
        assertEquals(result.getBorrowedDate().plusWeeks(2), result.getDueDate(), "Due date should be 2 weeks later");
    }

    //  TEST 2: Bok med 0 kopior ska kasta RuntimeException
    @Test
    void testLoanFailsWhenNoAvailableCopies() {
        // Arrange
        Book book = new Book();
        book.setId(2L);
        book.setTitle("Unavailable Book");
        book.setAvailableCopies(0);

        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Integration");
        user.setEmail("test@example.com");
        user.setPassword("123456");
        user = userRepository.save(user);


        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);

        // Act + Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> loanService.createLoan(loan));

        assertEquals("Book is not available for loan.", thrown.getMessage());
    }
}
