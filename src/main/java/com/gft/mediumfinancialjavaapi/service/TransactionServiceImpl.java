package com.gft.mediumfinancialjavaapi.service;

import com.gft.mediumfinancialjavaapi.exceptions.InvalidDateException;
import com.gft.mediumfinancialjavaapi.model.Transaction;
import com.gft.mediumfinancialjavaapi.model.TransactionTypeEnum;
import com.gft.mediumfinancialjavaapi.repository.Transactions;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  Transactions transactions;

  @Override
  public List<Transaction> findAll() {
    return transactions.findAll();
  }

  @Override
    public Transaction create(Transaction transaction) {


      if (transaction.getTransactionDate() != null && isTransactionInFuture(transaction)) {
          throw new InvalidDateException("Date must not be future");
      }

      if (transaction.getType().equals(TransactionTypeEnum.CARD) && transaction.getNsu() == null) {
          throw new DataIntegrityViolationException("NSU is mandatory when transaction type is CARD");
      }

        return transactions.save(transaction);
  }

  @Override
  public Transaction update(Long id, Transaction transaction) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Transaction findById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(Long id) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isTransactionInFuture(Transaction transaction) {
      return transaction.getTransactionDate().isAfter(LocalDateTime.now());
  }

}
