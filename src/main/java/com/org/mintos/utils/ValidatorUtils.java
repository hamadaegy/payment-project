package com.org.mintos.utils;

import com.org.mintos.dtos.AccountTransferRequestDTO;
import com.org.mintos.model.Account;

import java.math.BigDecimal;

public class ValidatorUtils {
    public static boolean isValidCurrencies(Account toAccountDBRecord, AccountTransferRequestDTO requestDTO) {
        return !toAccountDBRecord.getCurrency().equals(requestDTO.getCurrency());
    }

    public static boolean isNotEnoughBalanceForTransfer(Account fromAccountDBRecord, BigDecimal amount) {
        return fromAccountDBRecord.getBalance().compareTo(amount) <= -1;
    }

    public static boolean AreAccountsHavingNotSameCurrency(Account fromAccountDBRecord, Account toAccountDBRecord) {
        return !fromAccountDBRecord.getCurrency().equals(toAccountDBRecord.getCurrency());
    }
}
