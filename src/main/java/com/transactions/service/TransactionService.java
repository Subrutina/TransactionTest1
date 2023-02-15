package com.transactions.service;

import com.transactions.Person;
import com.transactions.Transaction;
import com.transactions.TransactionStatus;
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

    public List<Transaction> getClientTransactions(Integer id){
        return transactionRepository.findBySenderIdOrRecieverId(id);
    }
    public String getTransactions() {
        return transactionRepository.findAll().toString();
    }

    @Transactional
    public void addTransaction(Transaction tr) {

        Optional<Person> sender = personRepository.findById(tr.getSender().getJmbg());
        Optional<Person> receiver = personRepository.findById(tr.getReciever().getJmbg());

        if(sender.isPresent()){
            tr.setSender(sender.get());
        }else{
            personRepository.save(tr.getSender());
        }
        if(receiver.isPresent()){
            tr.setReciever(receiver.get());
        }else {
            personRepository.save(tr.getReciever());
        }
        transactionRepository.save(tr);


    }

    public void recieveTransaction(Transaction tr) {

        Optional<Transaction> currTransaction = transactionRepository.findById(tr.getId());
        if(currTransaction.isPresent()){
            Optional<Person> receiver = personRepository.findById(tr.getReciever().getJmbg());
            if(receiver.get().getName() != tr.getReciever().getName()){
                //obradi gresku
            }
            if(receiver.get().getSurname() != tr.getReciever().getSurname()){
                //obradi
            }
            if(receiver.get().getJmbg() != tr.getReciever().getJmbg()){
                //obradi
            }
            Integer currStatus = currTransaction.get().getStatus();
            if(currStatus != 0 ) {
                //obradi slucajeve
            }

            currTransaction.get().setStatus(TransactionStatus.COMPLETED);
            transactionRepository.save(currTransaction.get());

        }
        else{
            //baci izuzetak
        }



    }
}
