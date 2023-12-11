package com.org.mintos.services;

import com.org.mintos.configs.CustomPaging;
import com.org.mintos.model.Account;
import com.org.mintos.model.Transaction;
import com.org.mintos.repositories.AccountRepository;
import com.org.mintos.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;


    public Page<List<Transaction>> findTransactionsForTheAccount(String accountId, CustomPaging pageable) {
        Account account=accountRepository.findById(accountId).get();
        return transactionRepository.findByAccount(account, pageable);
    }
}
