package com.org.mintos.controllers;

import com.org.mintos.dtos.AccountDTO;
import com.org.mintos.dtos.AccountTransferRequestDTO;
import com.org.mintos.model.Account;
import com.org.mintos.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/transfer")
    public List<AccountDTO> transferBetweenAccounts(@RequestBody AccountTransferRequestDTO requestDTO) {
        return accountService.transferAmount(requestDTO);
    }
}
