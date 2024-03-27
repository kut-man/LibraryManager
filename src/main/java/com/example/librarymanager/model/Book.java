package com.example.librarymanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Optional;

@Entity
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Fill book's name")
    @Size(min = 2, max = 200, message = "Book's name must be between 2 and 200 characters")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Fill book's author")
    @Size(min = 2, max = 100, message = "Author's name must be between 2 and 100 characters")
    private String author;

    @Column(name = "year")
    @Max(value = 2050, message = "Incorrect year")
    @Min(value = 1000, message = "Incorrect year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Book(String name, String author, Integer year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
