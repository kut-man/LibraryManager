package com.example.librarymanager.repositories;

import com.example.librarymanager.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Integer> {
}
