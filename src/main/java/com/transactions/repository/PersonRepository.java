package com.transactions.repository;

import com.transactions.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository
        extends JpaRepository<Person, Integer> {
    @Query("SELECT p FROM Person p WHERE p.jmbg = ?1")
    Optional<Person> findById(String jmbg);


}
