package com.transactions.repository;

import com.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t WHERE t.sender.jmbg = :id OR t.receiver.jmbg = :id")
    List<Transaction> findBySenderIdOrReceiverId(@Param("id") String id);

    @Query("SELECT t FROM Transaction t WHERE t.lastModified BETWEEN :from and :to")
    List<Transaction> findByDate(@Param("from") LocalDateTime fromDate,
                                 @Param("to") LocalDateTime toDate);
}

