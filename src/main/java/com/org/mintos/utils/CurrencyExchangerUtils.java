package com.org.mintos.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class CurrencyExchangerUtils {

    @Value("${exchange.service.api}")
    private String url;

    @Value("${exchange.service.api.key}")
    private String apiKey;

    public BigDecimal convertTo(String fromAccountCurrency,String toCurrency, BigDecimal value) {
        String url_str = url + apiKey + "/latest/" + toCurrency;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url_str, String.class);
        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
        JsonObject mapOfCurrencies = jsonObject.get("conversion_rates").getAsJsonObject();
        BigDecimal currencyValue = mapOfCurrencies.get(fromAccountCurrency).getAsBigDecimal();
        return value.multiply(currencyValue);
    }
}
