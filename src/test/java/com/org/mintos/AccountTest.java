package com.org.mintos;

import com.org.mintos.dtos.AccountDTO;
import com.org.mintos.dtos.AccountTransferRequestDTO;
import com.org.mintos.model.Account;
import com.org.mintos.repositories.AccountRepository;
import com.org.mintos.services.AccountService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AccountTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    private AccountTransferRequestDTO accountTransferRequestDTO;

    public AccountTest() {
        initializeAccountRequestForTransfer();
    }

    private void initializeAccountRequestForTransfer() {
        this.accountTransferRequestDTO = new AccountTransferRequestDTO();
        this.accountTransferRequestDTO.setAmount(BigDecimal.valueOf(20.0));
        this.accountTransferRequestDTO.setCurrency("PLN");
        this.accountTransferRequestDTO.setFromAccountNumber("USD11-11-111");
        this.accountTransferRequestDTO.setToAccountNumber("PLN22-22-222");
    }

    @Test
    @Rollback(value = true)
    public void TestActualTwoAccountFORTransfer() {
        Account oldFromAccount = accountRepository.findById(this.accountTransferRequestDTO.getFromAccountNumber()).get();
        Account oldToAccount = accountRepository.findById(this.accountTransferRequestDTO.getToAccountNumber()).get();
        List<AccountDTO> accountDTOs = accountService.transferAmount(this.accountTransferRequestDTO);
        assertNotEquals(accountDTOs.size(), 0);
    }


    @Test
    public void TestTheDifferentCurrenciesException() {
        accountTransferRequestDTO.setCurrency("KWD");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            List<AccountDTO> accountDTOs = accountService.transferAmount(this.accountTransferRequestDTO);
        });

        accountTransferRequestDTO.setCurrency("PLN");
        assertTrue(exception.getMessage().contains("Transfer operation is not valid for the currency"));
    }

    @Test
    public void TestIfTheAccountHasNotEnoughBalanceException() {
        accountTransferRequestDTO.setAmount(BigDecimal.valueOf(1000));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            List<AccountDTO> accountDTOs = accountService.transferAmount(this.accountTransferRequestDTO);
        });
        assertTrue(exception.getMessage().contains("The balance in the account is not enough for transfer"));
    }

}
