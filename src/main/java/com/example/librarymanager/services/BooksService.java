package com.example.librarymanager.services;

import com.example.librarymanager.model.Book;
import com.example.librarymanager.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> showAll() {
        return booksRepository.findAll();
    }

    public void save(Book book) {
        booksRepository.save(book);
    }

    public void update(Book book) {
        booksRepository.save(book);
    }

    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Book find(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public List<Book> showAllSortedByYear() {
        return booksRepository.findAll(Sort.by("year"));
    }
}
