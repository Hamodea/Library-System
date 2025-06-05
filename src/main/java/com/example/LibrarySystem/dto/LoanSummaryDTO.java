package com.example.LibrarySystem.dto;

import java.time.LocalDateTime;

public class LoanSummaryDTO {

    private final Long id;
    private final String bookTitle;
    private final LocalDateTime borrowedDate;
    private final LocalDateTime dueDate;
    private final LocalDateTime returnedDate;
    private final String userFullName;

    // Constructor
    public LoanSummaryDTO(Long id, String bookTitle, LocalDateTime borrowedDate, LocalDateTime dueDate, LocalDateTime returnedDate, String userFullName) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnedDate = returnedDate;
        this.userFullName = userFullName;
    }

    // Getters (kan genereras automatiskt)

    public Long getId() {
        return id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public LocalDateTime getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getReturnedDate() {
        return returnedDate;
    }

    public String getUserFullName() {
        return userFullName;
    }

}
