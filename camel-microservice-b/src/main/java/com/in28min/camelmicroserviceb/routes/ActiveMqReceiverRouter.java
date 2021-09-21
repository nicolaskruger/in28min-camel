package com.in28min.camelmicroserviceb.routes;

import com.in28min.camelmicroserviceb.routes.porcesses.current_exchange.CurrentExchangeProcessor;
import com.in28min.camelmicroserviceb.routes.porcesses.current_exchange.CurrentExchangeTransform;
import com.in28min.camelmicroserviceb.routes.porcesses.matue.MatueFodaProcessor;
import com.in28min.camelmicroserviceb.routes.porcesses.matue.MatueFodaTransform;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private SplitterComponent splitterComponent;

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

//        from("activemq:my-active-xml-queue")
//                .unmarshal()
//                .jacksonxml(CurrencyExchange.class)
//                .bean(currentExchangeProcessor)
//                .bean(currentExchangeTransform)
//                .to("log:received-message-from-active-mq");
        from("activemq:split-queue")
                .convertBodyTo(String.class)
                .split(method(splitterComponent))
                .to("log:received-message-from-active-mq");
    }
}
@Component
class SplitterComponent{
    public List<String> splitInput(String body){
        return Arrays.stream(body.split(",")).collect(Collectors.toList());
    }
}
