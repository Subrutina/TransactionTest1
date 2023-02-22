package com.transactions.service;

import com.transactions.Person;
import com.transactions.Transaction;
import com.transactions.TransactionStatus;
import com.transactions.exceptions.PersonNotFoundException;
import com.transactions.exceptions.TransactionException;
import com.transactions.exceptions.TransactionNotFoundException;
import com.transactions.repository.PersonRepository;
import com.transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Transaction> getClientTransactions(String id){
        return transactionRepository.findBySenderIdOrReceiverId(id);
    }
    public List<Transaction> getTransactionsBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByDate(startDate, endDate);
    }

    public String getTransactions() {
        return transactionRepository.findAll().toString();
    }

    @Transactional
    public void addTransaction(Transaction tr) {

        Optional<Person> sender = personRepository.findById(tr.getSender().getJmbg());
        Optional<Person> receiver = personRepository.findById(tr.getReceiver().getJmbg());

        if(sender.isPresent()){
            tr.setSender(sender.get());
        }else{
            personRepository.save(tr.getSender());
        }
        if(receiver.isPresent()){
            tr.setReceiver(receiver.get());
        }else {
            personRepository.save(tr.getReceiver());
        }
        transactionRepository.save(tr);


    }

    public Transaction receiveTransaction(Transaction tr) throws TransactionException,
            TransactionNotFoundException,
            PersonNotFoundException {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(tr.getId());

        if (!optionalTransaction.isPresent()) {
            throw new TransactionNotFoundException("Transaction with id " + tr.getId() + " not found");
        }

        Transaction transaction = optionalTransaction.get();

        Optional<Person> optionalReceiver = personRepository.findById(tr.getReceiver().getJmbg());

        if (!optionalReceiver.isPresent()) {
            throw new PersonNotFoundException("Receiver with JMBG " + tr.getReceiver().getJmbg() + " not found");
        }

        Person receiver = optionalReceiver.get();

        if (!receiver.getName().equals(tr.getReceiver().getName())) {
            throw new TransactionException("Name not matching the transaction information");
        }

        if (!receiver.getSurname().equals(tr.getReceiver().getSurname())) {
            throw new TransactionException("Surname not matching the transaction information");
        }

        if (!transaction.isPending()) {
            if(transaction.isFailed()){
                throw new TransactionException("Transaction is failed.");
            }
            else if(transaction.isCancelled()){
                throw new TransactionException("Transaction is cancelled");
            }
            else if(transaction.isComplete()){
                throw  new TransactionException("Transcation is already complete");
            }
        }

        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setEndTime(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

}
