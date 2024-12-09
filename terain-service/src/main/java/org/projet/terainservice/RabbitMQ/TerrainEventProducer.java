package org.projet.terainservice.RabbitMQ;

import org.projet.terainservice.dtos.TerrainEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TerrainEventProducer {
    private final RabbitTemplate rabbitTemplate;
    private final String exchange;

    public TerrainEventProducer(RabbitTemplate rabbitTemplate,
                                @Value("${terrain.exchange}") String exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    public void sendEvent(String eventType, Object data) {
        rabbitTemplate.convertAndSend(exchange, "terrain_routing_key", new TerrainEvent(eventType, data));
    }
}
