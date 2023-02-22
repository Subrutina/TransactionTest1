package com.transactions.service;

import com.transactions.Person;
import com.transactions.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final PersonRepository personRepository;

    @Autowired
    public UserService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getUsers(){
        return personRepository.findAll();
    }
    public Optional<Person> getUserByJMBG(Integer jmbg){
        return personRepository.findById(jmbg);
    }




    public void addUser(Person p ) {
        personRepository.save(p);
    }
}
