package com.org.mintos.services;

import com.org.mintos.dtos.AccountDTO;
import com.org.mintos.dtos.AccountTransferRequestDTO;
import com.org.mintos.model.Account;
import com.org.mintos.model.Transaction;
import com.org.mintos.repositories.AccountRepository;
import com.org.mintos.repositories.TransactionRepository;
import com.org.mintos.utils.CurrencyExchangerUtils;
import com.org.mintos.utils.ValidatorUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CurrencyExchangerUtils currencyExchanger;

    @Transactional
    public List<AccountDTO> transferAmount(AccountTransferRequestDTO requestDTO) {
        Account fromAccountDBRecord = accountRepository.findByAccountNumber(requestDTO.getFromAccountNumber());
        Account toAccountDBRecord = accountRepository.findByAccountNumber(requestDTO.getToAccountNumber());

        if (ValidatorUtils.isValidCurrencies(toAccountDBRecord, requestDTO))
            throw new IllegalArgumentException("Transfer operation is not valid for the currency , please try again");

        BigDecimal actualRequiredAmount = requestDTO.getAmount();
        if (ValidatorUtils.AreAccountsHavingNotSameCurrency(fromAccountDBRecord, toAccountDBRecord))
            actualRequiredAmount = currencyExchanger.convertTo(fromAccountDBRecord.getCurrency(), toAccountDBRecord.getCurrency(), actualRequiredAmount);


        if (ValidatorUtils.isNotEnoughBalanceForTransfer(fromAccountDBRecord, actualRequiredAmount))
            throw new IllegalArgumentException("The balance in the account is not enough for transfer operation, please try again");


        toAccountDBRecord.setBalance(toAccountDBRecord.getBalance().add(requestDTO.getAmount()));
        fromAccountDBRecord.setBalance(fromAccountDBRecord.getBalance().subtract(actualRequiredAmount));
        List<Account> accounts = accountRepository.saveAll(Arrays.asList(fromAccountDBRecord, toAccountDBRecord));
        recordTransactionInDB(fromAccountDBRecord, toAccountDBRecord, requestDTO, actualRequiredAmount);
        return accounts.stream().map(Account::toDTO).collect(Collectors.toList());
    }

    private void recordTransactionInDB(Account fromAccountDBRecord, Account toAccountDBRecord, AccountTransferRequestDTO requestDTO, BigDecimal actualAmount) {
        String metaData = createMetaDataProcess(toAccountDBRecord);
        Transaction fromAccountTransaction = fillTransactionObject(fromAccountDBRecord, requestDTO, "-" + actualAmount.toString(), metaData);
        metaData = createMetaDataProcess(fromAccountDBRecord);
        Transaction toAccountTransaction = fillTransactionObject(toAccountDBRecord, requestDTO, "+" + requestDTO.getAmount().toString(), metaData);
        transactionRepository.saveAll(new LinkedList<>(Arrays.asList(fromAccountTransaction, toAccountTransaction)));
    }

    private String createMetaDataProcess(Account accountDBRecord) {
        return "account number " + accountDBRecord.getAccountNumber() + " client " + accountDBRecord.getClient().getName();
    }

    private Transaction fillTransactionObject(Account accountDBRecord, AccountTransferRequestDTO requestDTO, String actualAmount, String account) {
        Transaction accountTransaction = new Transaction(UUID.randomUUID());
        accountTransaction.setAccount(accountDBRecord);
        accountTransaction.setCurrency(accountDBRecord.getCurrency());
        accountTransaction.setAmount(actualAmount);
        accountTransaction.setFromToAccount(account);
        return accountTransaction;
    }


}
