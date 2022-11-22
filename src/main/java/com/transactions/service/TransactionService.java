package com.transactions.service;

import com.transactions.Person;
import com.transactions.Transaction;
import com.transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final UserService userService;
    private final TransactionRepository transactionRepository;


    @Autowired
    public TransactionService(UserService userService, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }


    public void addTransaction(Transaction tr) {

        transactionRepository.save(tr);

    }
}
