package com.in28min.camelmicroserviceb.routes.porcesses.matue;

import com.in28min.camelmicroserviceb.dto.MatueFoda;
import com.in28min.camelmicroserviceb.routes.ActiveMqReceiverRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MatueFodaProcessor {

    Logger logger = LoggerFactory.getLogger(ActiveMqReceiverRouter.class);


    public void   processMessage(MatueFoda matueFoda){

        logger.info("Oi eu sou {} !!!", matueFoda.getNome());

    }

}
