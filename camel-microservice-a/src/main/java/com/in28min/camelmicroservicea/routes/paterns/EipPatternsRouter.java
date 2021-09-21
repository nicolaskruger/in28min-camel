package com.in28min.camelmicroservicea.routes.paterns;

import com.in28min.camelmicroservicea.dto.CurrencyExchange;
import com.in28min.camelmicroservicea.dto.MatueDto;
import com.in28min.camelmicroservicea.routes.paterns.bean.DynamicRouterBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class EipPatternsRouter extends RouteBuilder {


    @Autowired
    private DynamicRouterBean dynamicRouterBean;

    private final String LOG_1 = "log:somethin1";
    private final String LOG_2 = "log:somethin2";
    private final String LOG_3 = "log:somethin3";
    private final MatueDto MATUE = MatueDto.builder()
            .apelido("tue")
            .nome("matue")
            .status("foda")
            .build();

//    @Override
    public void configure() throws Exception {
//        from("timer:multicast?period=10000")
//                .pipeline()
//                .routeId("rota matue muito louca")
//                .transform().constant(MATUE)
//                .multicast()
//                .to(LOG_1, LOG_2, LOG_3);

//        from("file:files/csv")
//                .unmarshal()
//                .csv()
//                .split(body())
//                .to("activemq:split-queue")
//                .to("log:split-files");

//        from("file:files/csv")
//                .convertBodyTo(String.class)
//                .split(body(),",")
//                .to("activemq:split-queue");

//        from("file:files/aggregate-json")
//                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
//                .aggregate(simple("${body.to}"), new ArrayListAggregatinStrategy())
//                .completionSize(3)
//                .completionTimeout(HIGHEST)
//                .to("log:aggregate-json");
        String routingSlip = "direct:endpoint1,direct:endpoint2";
//        String routingSlip = "direct:endpoint1,direct:endpoint2,direct:endpoint3";

//        from("timer:routingSlip?period=10000")
//                .transform().constant("gay")
//                .routingSlip(simple(routingSlip));

        getContext().setTracing(true);

        errorHandler(deadLetterChannel("activemq:dead-letter-queue"));

        from("{{endpoint-for-logging}}")
                .wireTap("")
                .to("log:directendpoint1");


        from("direct:endpoint2")
                .to("log:directendpoint2");

        from("direct:endpoint3")
                .to("log:directendpoint3");

        //Dynamic Routing

        // Step 1, Step 2, Step 3

        //Endpoint1
        //Endpoint2
        //Endpoint3

        from("timer:routingSlip?period=10000")
                .transform().constant("gay")
                .dynamicRouter(method(dynamicRouterBean));

    }



    class ArrayListAggregatinStrategy implements AggregationStrategy {
        //1,2,3

        //null, 1 => [1]
        //[1], 2 => [1,2]
        //[1,2], 3 => [1,2,3]

        @Override
        public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
            Object newBody = newExchange.getIn().getBody();
            ArrayList<Object> list = null;
            if (oldExchange == null) {
                list = new ArrayList<Object>();
                list.add(newBody);
                newExchange.getIn().setBody(list);
                return newExchange;
            } else {
                list = oldExchange.getIn().getBody(ArrayList.class);
                list.add(newBody);
                return oldExchange;
            }
        }
    }

}
