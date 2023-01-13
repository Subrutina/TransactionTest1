package com.transactions.service;

import com.transactions.Person;
import com.transactions.Transaction;
import com.transactions.repository.PersonRepository;
import com.transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final UserService userService;
    private final TransactionRepository transactionRepository;
    private final PersonRepository personRepository;


    @Autowired
    public TransactionService(UserService userService,
                              TransactionRepository transactionRepository,
                              PersonRepository personRepository) {
        this.userService = userService;
        this.transactionRepository = transactionRepository;
        this.personRepository = personRepository;
    }

    public String getTransactions() {
        return transactionRepository.findAll().toString();
    }

    @Transactional
    public void addTransaction(Transaction tr) {

        if(personRepository.findById(tr.getReciever().getJmbg()).isEmpty()) {
            personRepository.saveAndFlush(tr.getReciever());
        }
        if(personRepository.findById(tr.getSender().getJmbg()).isEmpty()) {
            personRepository.saveAndFlush(tr.getSender());
        }
        transactionRepository.save(tr);
        System.out.println(transactionRepository.findAll());
        System.out.println("alooooe\n");


    }
}
