package com.in28min.camelmicroservicea.routes.paterns.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class DynamicRouterBean {
    private int invocation = 0;
    public String decideTheNextEndpoint(
            @ExchangeProperties Map<String, String> properties,
            @Headers Map<String, String> headers,
            @Body String body
    ){
        log.info("{}{}{}", properties, headers, body);

        invocation++;
        int rest = invocation%3;

        if(rest == 0)
            return "{{endpoint-for-logging}}";
        if(rest == 1)
            return "direct:endpoint2";
        return "direct:endpoint3";
    }
}
