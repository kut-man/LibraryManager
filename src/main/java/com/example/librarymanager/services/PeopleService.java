package com.example.librarymanager.services;

import com.example.librarymanager.model.Person;
import com.example.librarymanager.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void update(int id, Person person) {
        peopleRepository.save(person);
    }

    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Person find(String fullName) {
        return peopleRepository.findByFullName(fullName);
    }

    public Person find(int id) {
        return peopleRepository.findById(id).orElse(null);
    }
}
