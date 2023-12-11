package com.org.mintos.controllers;

import com.org.mintos.dtos.ClientDTO;
import com.org.mintos.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/{id}")
    public ClientDTO findClientById(@PathVariable String id) {
        return clientService.findClientById(id);
    }
}
