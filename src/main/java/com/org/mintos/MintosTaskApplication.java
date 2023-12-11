package com.org.mintos;

import com.org.mintos.utils.ModelsCreatorUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class MintosTaskApplication {
    @Autowired
    private ModelsCreatorUtils modelsCreator;

    public static void main(String[] args) {
        SpringApplication.run(MintosTaskApplication.class, args);
    }

    @PostConstruct
    public void createDataModel() throws FileNotFoundException {
        modelsCreator.createDataModelBasedOnJsonFile("src/main/resources/clientsData.json");
    }
}
