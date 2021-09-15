package com.in28min.camelmicroserviceb.routes;

import com.in28min.camelmicroserviceb.dto.CurrencyExchange;
import com.in28min.camelmicroserviceb.routes.porcesses.current_exchange.CurrentExchangeProcessor;
import com.in28min.camelmicroserviceb.routes.porcesses.current_exchange.CurrentExchangeTransform;
import com.in28min.camelmicroserviceb.routes.porcesses.matue.MatueFodaProcessor;
import com.in28min.camelmicroserviceb.routes.porcesses.matue.MatueFodaTransform;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("kafka:myKafkaTopic")
                .to("log:received-message-from-kafka");
    }
}
