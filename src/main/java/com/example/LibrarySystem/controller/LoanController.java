package com.example.LibrarySystem.controller;

import com.example.LibrarySystem.dto.LoanRequest;
import com.example.LibrarySystem.dto.LoanSummaryDTO;
import com.example.LibrarySystem.entity.Book;
import com.example.LibrarySystem.entity.Loan;
import com.example.LibrarySystem.entity.User;
import com.example.LibrarySystem.mapper.LoanMapper;
import com.example.LibrarySystem.repository.BookRepository;
import com.example.LibrarySystem.repository.LoanRepository;
import com.example.LibrarySystem.repository.UserRepository;
import com.example.LibrarySystem.services.LoanService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanService loanService;

    public LoanController(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository, LoanService loanService) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.loanService = loanService;
    }

    @GetMapping
    public List<Loan>getAllLons(){
        return loanRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Loan>getLoanById(@PathVariable Long id){
        return loanService.getLoanId(id);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<LoanSummaryDTO>> getUserLoanSummary(@PathVariable Long userId) {
        List<Loan> loans = loanRepository.findByUserId(userId);

        if (loans.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<LoanSummaryDTO> summaries = loans.stream().map(loan -> {
            String fullName = loan.getUser().getFirstName() + " " + loan.getUser().getLastName();
            return new LoanSummaryDTO(
                    loan.getId(),
                    loan.getBook().getTitle(),
                    loan.getBorrowedDate(),
                    loan.getDueDate(),
                    loan.getReturnedDate(),
                    fullName
            );
        }).toList();

        return ResponseEntity.ok(summaries);
    }
    @PostMapping
    public ResponseEntity<?> loanBook(@RequestBody LoanRequest loanRequest) {
        // 1. Hämta user
        User user = userRepository.findById(loanRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Hämta book
        Book book = bookRepository.findById(loanRequest.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        //  3. Kontrollera om ett aktivt lån redan finns
        Optional<Loan> existingLoan = loanRepository.findActiveLoanByUserAndBook(user.getId(), book.getId());
        if (existingLoan.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User already has an active loan for this book."); // alt. skapa DTO för fel
        }

        // 4. Kontrollera tillgängliga exemplar
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is not available for loan.");
        }

        // 5. Skapa nytt lån
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);

        Loan saved = loanService.createLoan(loan);

        // 6. Returnera DTO
        LoanSummaryDTO dto = LoanMapper.toSummaryDTO(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }




    @PutMapping("/{id}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        // Hämta lån eller kasta exception
        Loan loan = loanService.getLoanId(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        System.out.println("DEBUG >> loan ID: " + loan.getId() + ", returnedDate = " + loan.getReturnedDate());


        if (loan.getReturnedDate() != null) {
            return ResponseEntity.badRequest().body("Book already returned");
        }


        // Sätt returnedDate till nu
        loan.setReturnedDate(LocalDateTime.now());

        // Uppdatera book.availableCopies
        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        bookRepository.save(book);
        loanRepository.save(loan);

        return ResponseEntity.ok("Book returned successfully");
    }

    @PutMapping("/{id}/extend")
    public ResponseEntity<String> extendLoan(@PathVariable Long id) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);

        if (optionalLoan.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan not found");
        }

        Loan loan = optionalLoan.get();

        if (loan.getReturnedDate() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Loan already returned, cannot extend");
        }

        // Förläng lånet med 7 dagar
        loan.setDueDate(loan.getDueDate().plusDays(7));
        loanRepository.save(loan);

        return ResponseEntity.ok("Loan extended to: " + loan.getDueDate());
    }



}
