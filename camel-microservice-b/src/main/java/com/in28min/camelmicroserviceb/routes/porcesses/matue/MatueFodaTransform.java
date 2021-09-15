package com.in28min.camelmicroserviceb.routes.porcesses.matue;

import com.in28min.camelmicroserviceb.dto.MatueFoda;
import com.in28min.camelmicroserviceb.routes.ActiveMqReceiverRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MatueFodaTransform {

    Logger logger = LoggerFactory.getLogger(ActiveMqReceiverRouter.class);


    public MatueFoda  processMessage(MatueFoda matueFoda){

        matueFoda.setNome(
                matueFoda.getNome() + "tue"
        );

        return matueFoda;
    }

}
