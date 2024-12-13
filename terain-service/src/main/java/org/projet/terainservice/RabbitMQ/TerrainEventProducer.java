package org.projet.terainservice.RabbitMQ;

import org.projet.terainservice.dtos.TerrainDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerrainEventProducer {

    private final RabbitTemplate rabbitTemplate;

    // Constructor
    public TerrainEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        // Set the Jackson2JsonMessageConverter on the RabbitTemplate
        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    // Publish assignment event (converted to JSON)
    public void publishEventAssignment(String routingKey, String event) {
        rabbitTemplate.convertAndSend("terrain.events", routingKey, event);
    }

    // Publish list of terrains (converted to JSON)
    public void publishEventList(String routingKey, String event) {
        rabbitTemplate.convertAndSend("terrain.events", routingKey, event);
    }

    // Publish single terrain event (converted to JSON)
    public void publishEventOneTerrain(String routingKey, String event) {
        rabbitTemplate.convertAndSend("terrain.events", routingKey, event);
    }
}
