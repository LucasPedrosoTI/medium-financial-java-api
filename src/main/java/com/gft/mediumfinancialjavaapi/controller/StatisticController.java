/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gft.mediumfinancialjavaapi.controller;

import com.gft.mediumfinancialjavaapi.service.StatisticService;
import com.gft.mediumfinancialjavaapi.service.TransactionService;
import com.gft.mediumfinancialjavaapi.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lps10
 */
@RestController
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StatisticService statisticsService;

    @GetMapping
    public Statistic getStatistic() {

        return statisticsService.create(transactionService.findAll());

    }
}
