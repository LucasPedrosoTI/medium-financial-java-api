package com.gft.mediumfinancialjavaapi.service;

import java.util.List;

import com.gft.mediumfinancialjavaapi.model.Transaction;

import org.springframework.web.bind.annotation.PathVariable;

public interface TransactionService {

  public Transaction create(Transaction transaction);

  public Transaction update(@PathVariable Long id, Transaction transaction);

  public Transaction findById(@PathVariable Long id);

  public List<Transaction> findAll();

  public void delete(@PathVariable Long id);

  public boolean isTransactionInFuture(Transaction transaction);
}
