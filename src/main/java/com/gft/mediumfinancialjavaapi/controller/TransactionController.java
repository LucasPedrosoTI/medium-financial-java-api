package com.gft.mediumfinancialjavaapi.controller;

import com.gft.mediumfinancialjavaapi.model.Transaction;
import com.gft.mediumfinancialjavaapi.service.TransactionService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public Transaction findById(@PathVariable Long id) {
        return transactionService.findById(id);
    }

    @PostMapping
    public Transaction create(@RequestBody @Valid Transaction transaction) {
        return transactionService.create(transaction);
    }

    @PutMapping("/{id}")
    public Transaction update(@RequestBody @Valid Transaction transaction, @PathVariable Long id) {
        return transactionService.update(id, transaction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }
}
