package com.org.mintos.controllers;

import com.org.mintos.configs.CustomPaging;
import com.org.mintos.model.Transaction;
import com.org.mintos.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping(value = "/{accountId}/{offset}/{limit}")
    Page<List<Transaction>> fetchTransactionForTheAccount(@PathVariable("accountId") String accountId, @PathVariable("offset") int offset, @PathVariable("limit") int limit) {
        CustomPaging customPaging=new CustomPaging(offset,limit,Sort.by("localDateTime").descending());
        return transactionService.findTransactionsForTheAccount(accountId, customPaging);
    }

}
