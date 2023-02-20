package com.transactions;


import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;
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
    private Person receiver;

    private Double amount;
    private Currency currency;

    private TransactionStatus status;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    public Transaction(){
        this.status = TransactionStatus.PENDING;
        this.beginDate = LocalDateTime.now();
        this.endDate = null;


    }
    public Transaction(Person sender, Person receiver, Double amount, Currency curr){
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.currency = curr;
        this.status = TransactionStatus.PENDING;
        this.beginDate = LocalDateTime.now();
        this.endDate = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(sender, that.sender) && Objects.equals(receiver, that.receiver) && Objects.equals(amount, that.amount) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, receiver, amount, currency);
    }

    public Integer getId() {
        return id;
    }
    public Integer getStatus() {
        if(status.equals(TransactionStatus.PENDING))
            return 0;
        else if(status.equals(TransactionStatus.CANCELLED))
            return 1;
        else if(status.equals(TransactionStatus.COMPLETED))
            return 2;
        else if(status.equals(TransactionStatus.FAILED))
            return 3;
        else
            return -1;
    }
    public boolean isPending(){
        return status == TransactionStatus.PENDING ? true : false;
    }
    public boolean isCancelled(){
        return status == TransactionStatus.CANCELLED ? true : false;
    }
    public boolean isComplete(){
        return status == TransactionStatus.COMPLETED ? true : false;
    }
    public boolean isFailed(){
        return status == TransactionStatus.FAILED ? true : false;
    }
    public Person getSender() {
        return sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public Double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", sender=" + sender +
                ", reciever=" + receiver +
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }

    public void setEndTime(LocalDateTime now) {
        this.endDate = now;
    }
}
