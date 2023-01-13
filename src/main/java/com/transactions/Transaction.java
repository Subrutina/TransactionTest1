package com.transactions;


import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Currency;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;


    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn (insertable= true, updatable = true, referencedColumnName="primaryK")
    private Person sender;



    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn (insertable= true, updatable = true, referencedColumnName="primaryK")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(sender, that.sender) && Objects.equals(reciever, that.reciever) && Objects.equals(amount, that.amount) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, reciever, amount, currency);
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
