package com.org.mintos.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.org.mintos.model.Account;
import com.org.mintos.model.Client;
import com.org.mintos.repositories.AccountRepository;
import com.org.mintos.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


@Component
public class ModelsCreatorUtils {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AccountRepository accountRepository;


    public void createDataModelBasedOnJsonFile(String path) throws FileNotFoundException {
        if (clientRepository.count() > 0) {
            System.out.println("******************data is exist Before **********************");
            return;
        }
        File f = new File(path);
        JsonObject object = JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(f)))).getAsJsonObject();
        JsonArray array = object.getAsJsonArray("clients");

        for (int i = 0; i < array.size(); ++i) {
            JsonObject jsonObject = array.get(i).getAsJsonObject();
            Client client = buildClientModelFromThe(jsonObject);
            client = clientRepository.saveAndFlush(client);
            storeTheAccountsForThat(client, jsonObject);
        }
        System.out.println("******************data is inserted successfully **********************");
    }

    private void storeTheAccountsForThat(Client client, JsonObject jsonObject) {
        JsonArray accountsJsonArray = jsonObject.getAsJsonArray("accounts");
        List<Account> accounts = new LinkedList<>();
        for (int i = 0; i < accountsJsonArray.size(); ++i) {
            JsonObject accountJsonObject = accountsJsonArray.get(i).getAsJsonObject();
            Account account = new Account();
            account.setAccountNumber(accountJsonObject.get("accountNumber").getAsString());
            account.setClient(client);
            account.setCurrency(accountJsonObject.get("currency").getAsString());
            account.setBalance(accountJsonObject.get("balance").getAsBigDecimal());
            accounts.add(account);
        }
        accountRepository.saveAllAndFlush(accounts);
    }


    private Client buildClientModelFromThe(JsonObject jsonObject) {
        Client client = new Client();
        client.setId(jsonObject.get("id").getAsString());
        client.setName(jsonObject.get("name").getAsString());
        client.setGender(jsonObject.get("gender").getAsString());
        client.setJob(jsonObject.get("job").getAsString());
        client.setTelephoneNumber(jsonObject.get("telephoneNumber").getAsString());
        client.setAddress(jsonObject.get("address").getAsString());
        return client;
    }

}


