package com.org.mintos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.org.mintos.dtos.AccountDTO;
import com.org.mintos.dtos.DTOEntityMapper;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Account implements DTOEntityMapper<AccountDTO> {

    @Id
    @Column(name = "account_Number")
    private String accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Transaction> transactions;

    private String currency;

    private BigDecimal balance;

    @Override
    public AccountDTO toDTO() {
        AccountDTO dto = new AccountDTO();
        dto.setAccountNumber(this.getAccountNumber());
        dto.setBalance(this.getBalance());
        dto.setCurrency(this.getCurrency());
        return dto;
    }
}
