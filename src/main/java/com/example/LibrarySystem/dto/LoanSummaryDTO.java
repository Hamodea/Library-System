package com.example.LibrarySystem.dto;

import java.time.LocalDateTime;

public class LoanSummaryDTO {

    private String bookTitle;
    private LocalDateTime borrowedDate;
    private LocalDateTime dueDate;
    private String userFullName;

    // Constructor
    public LoanSummaryDTO(String bookTitle, LocalDateTime borrowedDate, LocalDateTime dueDate, String userFullName) {
        this.bookTitle = bookTitle;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.userFullName = userFullName;
    }

    // Getters (kan genereras automatiskt)
    public String getBookTitle() {
        return bookTitle;
    }

    public LocalDateTime getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getUserFullName() {
        return userFullName;
    }
}
