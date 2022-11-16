package com.transactions;


import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Currency;
import java.util.List;

@Entity
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn (insertable= false, updatable = false, name="senderId", referencedColumnName  = "primaryK" )
    private Person sender;


    @ManyToOne
    @JoinColumn (insertable= false, updatable = false, name="recieverId", referencedColumnName="primaryK")
    private Person reciever;

    private Double amount;
    private Currency currency;


    public Transaction(Person sender, Person reciever, Double amount, Currency currency) {
        this.sender = sender;
        this.reciever = reciever;
        this.amount = amount;
        this.currency = currency;
    }

    public Transaction() {
    }

    public Integer getId() {
        return id;
    }

    public Person getSender() {
        return sender;
    }

    public Person getReciever() {
        return reciever;
    }

    public Double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
