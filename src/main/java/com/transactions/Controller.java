package com.transactions;


import com.transactions.repository.TransactionRepository;
import com.transactions.service.TransactionService;
import com.transactions.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public String getTransactions(){
        return transactionService.getTransactions();
    }

    @PostMapping
    public void newTransaction(@RequestBody Transaction tr) {
      transactionService.addTransaction(tr);
    }
}
