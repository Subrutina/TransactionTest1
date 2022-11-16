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

        //da li onaj koji salje pare vec postoji u bazi?
        boolean p1, p2 = false;
        Optional<Person> sender = userService.getUserByJMBG(tr.getSender().getJmbg());
        if(sender.isPresent()) {
            System.out.println("test1");

            tr.getSender().setCity(sender.get().getCity());
            tr.getSender().setCountry(sender.get().getCountry());
            tr.getSender().setPostalNo(sender.get().getPostalNo());
        }
        else{//ako ne postoji
            userService.addUser(tr.getSender());
            p1 = true;
        }

        //da li onaj koji prima pare vec postoji u bazi
        Optional<Person> reciever = userService.getUserByJMBG(tr.getReciever().getJmbg());
        if(reciever.isPresent()) {
            System.out.println("test");
            tr.getReciever().setCity(reciever.get().getCity());
            tr.getReciever().setCountry(reciever.get().getCountry());
            tr.getReciever().setPostalNo(reciever.get().getPostalNo());
        }
        else{
            System.out.println("test2");
            userService.addUser(tr.getReciever());
            p2 = true;
        }

        //kada se otkomentarise ova linija ispod, dva puta mi cuva
        transactionRepository.save(tr);

    }
}
