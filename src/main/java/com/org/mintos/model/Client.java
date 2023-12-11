package com.org.mintos.model;

import com.org.mintos.dtos.AccountDTO;
import com.org.mintos.dtos.ClientDTO;
import com.org.mintos.dtos.DTOEntityMapper;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Client implements DTOEntityMapper<ClientDTO> {
    @Id
    private String id;
    private String name;
    private String gender;
    private String address;
    private String job;
    private String telephoneNumber;
    private int age;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Account> accounts;


    @Override
    public ClientDTO toDTO() {
        ClientDTO dto = new ClientDTO();
        dto.setId(this.getId());
        dto.setAge(this.getAge());
        dto.setName(this.getName());
        dto.setGender(this.getGender());
        dto.setTelephoneNumber(this.getTelephoneNumber());
        dto.setAddress(this.getAddress());
        List<AccountDTO> accountsDTO = this.getAccounts().stream().map(Account::toDTO).collect(Collectors.toList());
        dto.setAccounts(accountsDTO);
        return dto;
    }
}
