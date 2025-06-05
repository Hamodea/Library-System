package com.example.LibrarySystem.mapper;


import com.example.LibrarySystem.dto.LoanSummaryDTO;
import com.example.LibrarySystem.entity.Loan;

public class LoanMapper {

    public static LoanSummaryDTO toSummaryDTO(Loan loan) {
        String fullName = loan.getUser().getFirstName() + " " + loan.getUser().getLastName();

        return new LoanSummaryDTO(
                loan.getId(),
                loan.getBook().getTitle(),
                loan.getBorrowedDate(),
                loan.getDueDate(),
                loan.getReturnedDate(),
                fullName
        );
    }
}
