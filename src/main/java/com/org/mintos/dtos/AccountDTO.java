package com.org.mintos.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private String accountNumber;
    private String currency;
    private BigDecimal balance;
}
