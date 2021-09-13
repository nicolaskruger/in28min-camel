package com.in28min.camelmicroservicea.routes.a;


import lombok.Data;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.language.bean.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class MyFirstTimerRouter extends RouteBuilder {

    private final String TIMER_ROUTE = "timer:first-timer";

    private final String LOG_ROUTE = "log:first-timer";

    @Override
    public void configure() throws Exception {
        from(TIMER_ROUTE)
//                .transform().constant("megaman")
                .bean(GetCurrentMatueBean.class, "getCurrentMatue")
                .to(LOG_ROUTE);
    }

    @Component
    class GetCurrentMatueBean{
        public String getCurrentMatue(){
            return  "Time now is" + LocalDateTime.now();
        }
    }
}
