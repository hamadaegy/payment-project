package com.org.mintos;

import com.org.mintos.dtos.ClientDTO;
import com.org.mintos.services.ClientService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientTest {

    @Autowired
    private ClientService clientService;


    @Test
    public void checkfetchClientsAccountInfoTest() {
        ClientDTO dto = clientService.findClientById("1");
        assertFalse(dto == null);
    }
}
