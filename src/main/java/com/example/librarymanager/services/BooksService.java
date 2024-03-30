package com.example.librarymanager.services;

import com.example.librarymanager.model.Book;
import com.example.librarymanager.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public List<Book> showAll(Integer page, Integer perPage, Boolean asc) {
        if (asc != null) {
            if(page != null && perPage != null) {
                if (asc) {
                    return booksRepository.findAll(PageRequest.of(page, perPage, Sort.by("year").ascending())).getContent();
                }
                return booksRepository.findAll(PageRequest.of(page, perPage, Sort.by("year").descending())).getContent();
            }
            if(asc) return booksRepository.findAll(Sort.by("year").ascending());
            return booksRepository.findAll(Sort.by("year").descending());
        }
        else if(page != null && perPage != null) {
            return booksRepository.findAll(PageRequest.of(page, perPage)).getContent();
        }
        return booksRepository.findAll();
    }
}
