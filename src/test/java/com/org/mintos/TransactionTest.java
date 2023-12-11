package com.org.mintos;

import com.org.mintos.configs.CustomPaging;
import com.org.mintos.model.Transaction;
import com.org.mintos.services.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class TransactionTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testFetchingUserAccount() {
        CustomPaging customPaging = new CustomPaging(0, 2, Sort.by("localDateTime").descending());
        Page<List<Transaction>> transactionList = transactionService.findTransactionsForTheAccount("USD11-11-111", customPaging);
        Assertions.assertTrue(transactionList.getSize() !=0);

    }


}
