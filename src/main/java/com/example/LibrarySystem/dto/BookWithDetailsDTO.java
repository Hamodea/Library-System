package com.example.LibrarySystem.dto;

public class BookWithDetailsDTO {
    private final Long id;
    private final String title;
    private final int publicationYear;
    private final int availableCopies;
    private final int totalCopies;

    private final String authorFirstName;
    private final String authorLastName;

    public BookWithDetailsDTO(Long id, String title, int publicationYear, int availableCopies, int totalCopies, String authorFirstName, String authorLastName) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public int getPublicationYear() { return publicationYear; }
    public int getAvailableCopies() { return availableCopies; }
    public int getTotalCopies() { return totalCopies; }
    public String getAuthorFirstName() { return authorFirstName; }
    public String getAuthorLastName() { return authorLastName; }
}
