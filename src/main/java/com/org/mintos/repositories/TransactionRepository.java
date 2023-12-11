package com.org.mintos.repositories;

import com.org.mintos.configs.CustomPaging;
import com.org.mintos.model.Account;
import com.org.mintos.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>, PagingAndSortingRepository<Transaction, String> {
    Page<List<Transaction>> findByAccount(Account account, CustomPaging pageable);
}
