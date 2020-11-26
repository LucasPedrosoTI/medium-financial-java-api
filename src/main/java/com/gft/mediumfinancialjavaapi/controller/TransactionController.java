package com.gft.mediumfinancialjavaapi.controller;

import com.gft.mediumfinancialjavaapi.model.Transaction;
import com.gft.mediumfinancialjavaapi.service.TransactionService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  @Autowired
  TransactionService transactionService;

  @GetMapping
  public List<Transaction> findAll() {
    return transactionService.findAll();
    }

    @PostMapping
    public Transaction create(@RequestBody @Valid Transaction transaction) {
        return transactionService.create(transaction);
    }
}
