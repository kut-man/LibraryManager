package com.example.librarymanager.repositories;

import com.example.librarymanager.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameContainingIgnoreCase(String query);
}
