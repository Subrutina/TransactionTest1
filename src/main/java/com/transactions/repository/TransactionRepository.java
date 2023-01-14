package com.transactions.repository;

import com.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t WHERE t.sender.id = :id OR t.reciever.id = :id")
    List<Transaction> findBySenderIdOrRecieverId(@Param("id") Integer id);

}

