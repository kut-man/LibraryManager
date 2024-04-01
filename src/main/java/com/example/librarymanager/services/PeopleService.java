package com.example.librarymanager.services;

import com.example.librarymanager.model.Book;
import com.example.librarymanager.model.Person;
import com.example.librarymanager.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> showAll() {
        return peopleRepository.findAll();
    }

    public void save(Person person) {
        peopleRepository.save(person);
    }

    public void update(Person person) {
        peopleRepository.save(person);
    }

    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Person find(String fullName) {
        return peopleRepository.findByFullName(fullName);
    }

    public Person find(int id) {
        Person person = peopleRepository.findById(id).orElse(null);
        if (person != null) {
            for (Book book : person.getBooks()){
                long now = new Date().getTime();
                boolean isExpired = (now - 864000000) > book.getTakenAt().getTime();
                book.setExpired(isExpired);
            }
        }
        return person;
    }
}
