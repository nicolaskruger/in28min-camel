package com.in28min.camelmicroservicea.routes.a;


import lombok.Data;
import lombok.extern.slf4j.XSlf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Data
//@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentMatueBean getCurrentMatueBean;

    @Autowired
    private SimpleLoggingProcessingComponent loggingProcessingComponent;

    private final String TIMER_ROUTE = "timer:first-timer";

    private final String LOG_ROUTE = "log:first-timer";

    @Override
    public void configure() throws Exception {
        from(TIMER_ROUTE)
//                .transform().constant("megaman")
                .log("${body}")
                .bean(getCurrentMatueBean, "getCurrentMatue")
                .log("${body}")
                .bean(loggingProcessingComponent)
                .log("${body}")
                .process(new SimploLogginProcessor())
                .to(LOG_ROUTE);
    }

    @Component
    class GetCurrentMatueBean{
        public String getCurrentMatue(){
            return  "Time now is" + LocalDateTime.now();
        }
    }

    @Component
    class SimpleLoggingProcessingComponent {

        private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

        public void process(String message){
            logger.info("SimpleLoggingProcessingComponent {}", message);
        }
    }

    class SimploLogginProcessor implements Processor {

        private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

        @Override
        public void process(Exchange exchange) throws Exception {
            logger.info("SimpleLoggingProcessing {}", exchange.getMessage().getBody());
        }
    }
}
