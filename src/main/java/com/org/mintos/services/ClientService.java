package com.org.mintos.services;

import com.org.mintos.dtos.ClientDTO;
import com.org.mintos.model.Client;
import com.org.mintos.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO findClientById(String id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty())
            throw new IllegalArgumentException("This id is not exist");
        return optionalClient.get().toDTO();
    }
}
