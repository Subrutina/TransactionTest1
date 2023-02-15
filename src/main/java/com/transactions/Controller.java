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

    @GetMapping("/transactions")
    public String getTransactions(){
        return transactionService.getTransactions();
    }

    @GetMapping("/clientTransactions/{id}")
    public List<Transaction> getClientTransaction(@PathVariable Integer id){
        return transactionService.getClientTransactions(id);
    }

    @PostMapping
    public void newTransaction(@RequestBody Transaction tr) {
      transactionService.addTransaction(tr);
    }

    @PostMapping("/getMoney")
    public void recieveMoney(@RequestBody Transaction tr){
        transactionService.recieveTransaction(tr);
    }
}
