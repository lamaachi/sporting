package org.projet.centreservice.RabbitMQ;

import org.projet.centreservice.dtos.CentreEvent;
import org.projet.centreservice.dtos.CentreSportifDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CentreEventProducer {
//    private final RabbitTemplate rabbitTemplate;
//    private final String exchange;
//
//    public CentreEventProducer(RabbitTemplate rabbitTemplate,
//                                @Value("${centre.exchange}") String exchange) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.exchange = exchange;
//    }
//
//    public void sendEvent(String eventType, Object data) {
//        rabbitTemplate.convertAndSend(exchange, "centre_routing_key", new CentreEvent(eventType, data));
//    }
}
