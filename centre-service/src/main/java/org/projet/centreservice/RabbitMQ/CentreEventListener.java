package org.projet.centreservice.RabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.projet.centreservice.dtos.CentreSportifDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author lamaachi
 **/
@Service
@Slf4j
public class CentreEventListener {
    @RabbitListener(queues = "centre.queue")
    public void handleCentreCreatedEvent(CentreSportifDTO centreSportifDTO) {
        System.out.println("Received event:"+ centreSportifDTO);
        // Traitez l'événement ici (par exemple, mise à jour dans un autre système)
    }
}
