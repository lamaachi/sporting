package org.projet.terainservice.RabbitMQ;

import org.projet.terainservice.dtos.TerrainAssignmentEvent;
import org.projet.terainservice.dtos.TerrainDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerrainEventProducer {
    private final RabbitTemplate rabbitTemplate;

    public TerrainEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void publishEventAssignment(String routingKey, TerrainAssignmentEvent event) {
        rabbitTemplate.convertAndSend("terrain.events", routingKey, event);
    }

    public void publishEventList(String routingKey, List<TerrainDTO> event) {
        rabbitTemplate.convertAndSend("terrain.events", routingKey, event);
    }

    public void publishEventOneTerrain(String routingKey, TerrainDTO event) {
        rabbitTemplate.convertAndSend("terrain.events", routingKey, event);
    }

}
