package com.org.mintos.dtos;

import com.org.mintos.model.Client;
import lombok.Data;

import java.util.List;

@Data
public class ClientDTO implements EntityMapper<Client> {
    private String id;
    private String name;
    private String gender;
    private String address;
    private String job;
    private String telephoneNumber;
    private int age;
    private List<AccountDTO> accounts;


    @Override
    public Client toEntity() {
        return null;
    }
}
