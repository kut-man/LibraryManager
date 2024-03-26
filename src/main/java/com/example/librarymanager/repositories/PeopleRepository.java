package com.example.librarymanager.repositories;

import com.example.librarymanager.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
