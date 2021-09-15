package com.in28min.camelmicroserviceb.controller;

import com.in28min.camelmicroserviceb.dto.CurrencyExchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange findConversionValue(
            @PathVariable String from,
            @PathVariable String to
    ){
        return CurrencyExchange.builder()
                .conversionMultiple(BigDecimal.TEN)
                .id(1000L)
                .from(from)
                .to(to)
                .build();
    }

}
