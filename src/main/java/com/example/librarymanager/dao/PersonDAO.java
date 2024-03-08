package com.example.librarymanager.dao;

import com.example.librarymanager.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> showAll() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(full_name, birth_date) VALUES(?, TO_DATE(?, 'MM/DD/YYYY'))", person.getFullName(), person.getBirthDate());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET full_name=?, birth_date=TO_DATE(?, 'MM/DD/YYYY') WHERE id = ?", person.getFullName(), person.getBirthDate(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }

    public Optional<Person> find(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name = ?", new BeanPropertyRowMapper<>(Person.class), fullName).stream().findAny();
    }

    public Person find(int id) {
        return jdbcTemplate.query("SELECT id, full_name, TO_CHAR(birth_date, 'MM/DD/YYYY') as birth_date FROM Person WHERE id = ?", new BeanPropertyRowMapper<>(Person.class), id).stream().findFirst().orElse(null);
    }
}
