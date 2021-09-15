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
public class ActiveMqReceiverRouter extends RouteBuilder {

    @Autowired
    private CurrentExchangeProcessor currentExchangeProcessor;

    @Autowired
    private CurrentExchangeTransform currentExchangeTransform;

    @Autowired
    private MatueFodaProcessor matueFodaProcessor;

    @Autowired
    private MatueFodaTransform matueFodaTransform;

    @Override
    public void configure() throws Exception {

        //JSON
        //CurrencyExchange
        //
//        from("activemq:my-active-queue")
//                .unmarshal()
//                .json(JsonLibrary.Jackson, MatueFoda.class)
//                .bean(matueFodaProcessor)
//                .bean(matueFodaTransform)
//                .to("log:received-message-from-active-mq");

        from("activemq:my-active-xml-queue")
                .unmarshal()
                .jacksonxml(CurrencyExchange.class)
                .bean(currentExchangeProcessor)
                .bean(currentExchangeTransform)
                .to("log:received-message-from-active-mq");
    }
}
