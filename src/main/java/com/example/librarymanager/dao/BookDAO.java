package com.example.librarymanager.dao;

import com.example.librarymanager.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showAll() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=?, person_id=? WHERE id = ?", book.getName(), book.getAuthor(), book.getYear(), book.getPersonId(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

    public Book find(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id = ?", new BeanPropertyRowMapper<>(Book.class), id).getFirst();
    }

    public List<Book> findPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?", new BeanPropertyRowMapper<>(Book.class), id);
    }
}
