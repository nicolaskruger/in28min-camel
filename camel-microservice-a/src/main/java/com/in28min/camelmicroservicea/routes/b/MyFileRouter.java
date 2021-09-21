package com.in28min.camelmicroservicea.routes.b;

import com.in28min.camelmicroservicea.dto.MatueDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component
@Slf4j
public class MyFileRouter extends RouteBuilder {

    @Autowired
    private DeciderBean deciderBean;

    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .routeId("Files-Input-Route")
                .log("${body}")
                .setProperty("retorno",constant("${body}"))
                .transform().body(String.class)
                .choice()
                    .when(simple("${file:ext} == 'xml'"))
                        .log("XML FILE")
                    .when(simple("${file:ext} ends with 'json' && ${body} contains 'matue' "))
                        .unmarshal()
                        .json(JsonLibrary.Jackson, MatueDto.class)
                            .choice()
                                .when(method(deciderBean))
                                    .log("é o matue foda")
                                .otherwise()
                                    .log("não é o matue foda")
                            .endChoice()
                        .log("json FILE")
                    .otherwise()
                        .log("Not an XML file")
                .end()
                .to("direct:log-file-values")
                .transform(simple("${exchangeProperty.retorno}"))
                .log("${body}")
                .to("file:files/output");

        from("direct:log-file-values")
                .routeId("Log-file")
                .log("${messageHistory}")
                .log(" fin ${body}");
    }
    @Component
    class DeciderBean {
        public boolean isThisConditionMet(@Body MatueDto body,
                                          @Headers Map<String, String> headers,
                                          @ExchangeProperties Map<String, String> exchangePropeties){
            log.info(body.getNome(), headers, exchangePropeties);
            return body.getNome().equals("matue");
        }
    }
}
