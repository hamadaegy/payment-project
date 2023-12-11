package com.org.mintos.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountTransferRequestDTO {

    private String fromAccountNumber;
    private String toAccountNumber;
    private String currency;
    private BigDecimal amount;
}
