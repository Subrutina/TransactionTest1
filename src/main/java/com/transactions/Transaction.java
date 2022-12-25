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


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn (insertable= false, updatable = false, referencedColumnName="primaryK")
    private Person sender;



    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn (insertable= false, updatable = false, referencedColumnName="primaryK")
    private Person reciever;

    private Double amount;
    private Currency currency;

    public Transaction(){}
    public Transaction(Person sender, Person reciever, Double amount, Currency curr){
        this.sender = sender;
        this.reciever = reciever;
        this.amount = amount;
        this.currency = curr;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", sender=" + sender +
                ", reciever=" + reciever +
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}

