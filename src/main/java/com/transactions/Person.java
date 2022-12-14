package com.transactions;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Person {
    @Id
    @Column(name = "primaryK")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer primaryK;

    private String jmbg;
    private String name;
    private String surname;
    private String city;
    private Integer postalNo;
    private String country;

    @OneToMany(mappedBy = "sender")
    private Set<Transaction> sendTransactions;


    @OneToMany(mappedBy = "reciever")
    private Set<Transaction> recieveTransactions;


    public Person() {
    }
    public Person(String jmbg, String name, String surname){
        this.jmbg = jmbg;
        this.name = name;
        this.surname = surname;
    }
    public Person(String name,
                  String surname,
                  String city,
                  String country,
                  Integer postalNo,
                  String jmbg) {
        this.jmbg = jmbg;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.postalNo = postalNo;
        this.country = country;
    }

    public String getJmbg() {
        return jmbg;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public Integer getPostalNo() {
        return postalNo;
    }

    public String getCountry() {
        return country;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalNo(Integer postalNo) {
        this.postalNo = postalNo;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Person{" +
                "primaryK=" + primaryK +
                ", jmbg='" + jmbg + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", postalNo=" + postalNo +
                ", country='" + country + '\'' +
                '}';
    }
}

