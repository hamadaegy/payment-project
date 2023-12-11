package com.org.mintos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Transaction {
    @Id
    @Column(name = "transaction_Id")
    private String transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;
    private String fromToAccount;
    private String amount;
    private String currency;
    @CreationTimestamp
    LocalDateTime localDateTime;

    public Transaction(UUID randomUUID) {
        this.transactionId = randomUUID.toString();
    }
}
