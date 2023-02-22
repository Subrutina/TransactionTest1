package com.transactions;


import com.transactions.exceptions.PersonNotFoundException;
import com.transactions.exceptions.TransactionException;
import com.transactions.exceptions.TransactionNotFoundException;
import com.transactions.repository.TransactionRepository;
import com.transactions.service.TransactionService;
import com.transactions.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.List;

@RestController
@RequestMapping(path="/home")
public class Controller {

    private TransactionService transactionService;
    private UserService userService;

    public Controller(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping("/clientTransactions")
    public List<Transaction> getClientTransactions(@RequestParam("id") String id){
        return transactionService.getClientTransactions(id);
    }
    @GetMapping("/transactions")
    public String getTransactions(){
        return transactionService.getTransactions();
    }

    @GetMapping("/transactionsBetweenDates")
    public List<Transaction> getTransactionsBetweenDates(@RequestParam("fromDate") String from,
                                                         @RequestParam("toDate") String to){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDate = LocalDateTime.parse(from, formatter);
        LocalDateTime toDate = LocalDateTime.parse(to, formatter);

        return transactionService.getTransactionsBetween(fromDate, toDate);

    }

    @PostMapping
    public void newTransaction(@RequestBody Transaction tr) {
      transactionService.addTransaction(tr);
    }

    @PostMapping("/getMoney")
    Transaction receiveMoney(@RequestBody Transaction tr) throws TransactionException, PersonNotFoundException, TransactionNotFoundException {
        return transactionService.receiveTransaction(tr);
    }
}
