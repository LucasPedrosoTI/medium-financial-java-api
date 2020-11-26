package com.gft.mediumfinancialjavaapi.service;

import com.gft.mediumfinancialjavaapi.exceptions.InvalidDateException;
import com.gft.mediumfinancialjavaapi.model.Transaction;
import com.gft.mediumfinancialjavaapi.model.TransactionTypeEnum;
import com.gft.mediumfinancialjavaapi.repository.Transactions;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    Transactions transactions;

    public List<Transaction> findAll() {
        return transactions.findAll();
    }

    public Transaction create(Transaction transaction) {

        validadeDate(transaction);

        validateNSU(transaction);

        return transactions.save(transaction);
    }

    public Transaction update(Long id, Transaction transaction) {
        validadeDate(transaction);
        validateNSU(transaction);

        Transaction existingTransaction = findTransactionById(id);

        BeanUtils.copyProperties(transaction, existingTransaction, "id");

        return transactions.save(existingTransaction);
    }

    public Transaction findById(Long id) {
        return findTransactionById(id);
    }

    public void delete(Long id) {
        transactions.deleteById(id);
    }

    public boolean isTransactionInFuture(Transaction transaction) {
        return transaction.getTransactionDate().isAfter(LocalDateTime.now());
    }

    private void validadeDate(Transaction transaction) throws InvalidDateException {
        if (transaction.getTransactionDate() != null && isTransactionInFuture(transaction)) {
            throw new InvalidDateException("Date must not be future");
        }
    }

    private void validateNSU(Transaction transaction) throws DataIntegrityViolationException {
        if (transaction.getType().equals(TransactionTypeEnum.CARD) && transaction.getNsu() == null) {
            throw new DataIntegrityViolationException("NSU is mandatory when transaction type is CARD");
        }
    }

    private Transaction findTransactionById(Long id) throws EmptyResultDataAccessException {
        return transactions.findById(id).orElseGet(() -> {
            throw new EmptyResultDataAccessException(1);
        });
    }

}
