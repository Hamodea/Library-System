package com.example.LibrarySystem.dto;

public class BookDTO {
    private Long id;
    private String title;
    private int publicationYear;
    private int availableCopies;
    private int totalCopies;

    public BookDTO(Long id, String title, int publicationYear, int availableCopies, int totalCopies) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public int getPublicationYear() { return publicationYear; }
    public int getAvailableCopies() { return availableCopies; }
    public int getTotalCopies() { return totalCopies; }
}
