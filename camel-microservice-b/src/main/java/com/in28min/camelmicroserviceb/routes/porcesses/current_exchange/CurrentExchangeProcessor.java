package com.in28min.camelmicroserviceb.routes.porcesses.current_exchange;

import com.in28min.camelmicroserviceb.dto.CurrencyExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CurrentExchangeProcessor {



    public void processor(CurrencyExchange currencyExchange){
        log.info("um processo muto louco {}", currencyExchange.getId());
    }
}
