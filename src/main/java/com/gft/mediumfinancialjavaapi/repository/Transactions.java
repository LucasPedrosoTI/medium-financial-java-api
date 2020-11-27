package com.gft.mediumfinancialjavaapi.repository;

import com.gft.mediumfinancialjavaapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Transactions extends JpaRepository<Transaction, Long> {
    public Transaction findByNsu(String nsu);
}
