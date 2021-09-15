package com.in28min.camelmicroserviceb.routes.porcesses.current_exchange;

import com.in28min.camelmicroserviceb.dto.CurrencyExchange;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CurrentExchangeTransform {
    public CurrencyExchange processor(CurrencyExchange currencyExchange){

        currencyExchange.setConversionMultiple(
                currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN)
        );

        return  currencyExchange;

    }
}
