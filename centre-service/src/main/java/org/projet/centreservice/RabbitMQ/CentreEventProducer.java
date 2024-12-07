package org.projet.centreservice.RabbitMQ;

import org.projet.centreservice.dtos.CentreSportifDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentreEventProducer {
    private final RabbitTemplate rabbitTemplate;

        @Autowired
        public CentreEventProducer(RabbitTemplate rabbitTemplate) {
            this.rabbitTemplate = rabbitTemplate;
        }

    //    public void sendCentreEvent(String centreEvent) {
    //        rabbitTemplate.convertAndSend("centre.exchange", "centre.routing.key", centreEvent);
    //    }

        public void sendCentreCreatedEvent(CentreSportifDTO centreSportifDTO) {
            rabbitTemplate.convertAndSend("centre.exchange", "centre.routing.key", centreSportifDTO);
            System.out.println("Event sent: "+centreSportifDTO);
        }
    }
