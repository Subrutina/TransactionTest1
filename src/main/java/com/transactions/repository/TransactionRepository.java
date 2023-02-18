package com.transactions.repository;

import com.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t WHERE t.sender.id = :id OR t.receiver.id = :id")
    List<Transaction> findBySenderIdOrReceiverId(@Param("id") Integer id);

}

